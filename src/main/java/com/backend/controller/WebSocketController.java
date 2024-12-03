package com.backend.controller;

import com.backend.dto.request.calendar.PutCalendarDto;
import com.backend.entity.calendar.CalendarMapper;
import com.backend.entity.user.User;
import com.backend.repository.UserRepository;
import com.backend.repository.calendar.CalendarMapperRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    private final Map<String, List<String>> userCalendarSubscriptions = new HashMap<>(); // userId -> List<calendarId>
    private final UserRepository userRepository;
    private final CalendarMapperRepository calendarMapperRepository;

    @MessageMapping("/calendar/update")
    public void updateCalendar(String message, String calendarId) {
        System.out.println("==================12312312321==========================");
        // 구독자 목록 가져오기
        for (Map.Entry<String, List<String>> entry : userCalendarSubscriptions.entrySet()) {
            String userId = entry.getKey();
            List<String> subscribedCalendars = entry.getValue();

            // 구독한 캘린더에 해당하는 사용자가 있을 경우 메시지 전송
            if (subscribedCalendars.contains("19")) {
                System.out.println("==================12312312321==========================");
                messagingTemplate.convertAndSend( "/topic/calendar/19", "{\"message\": \"Subscribed to calendar: " + 19 + "\"}");
            }
        }
    }

    // 특정 사용자가 캘린더를 구독
    @MessageMapping("/subscribe")
    public void subscribeToCalendar(@Payload String jsonMessage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> messageMap = objectMapper.readValue(jsonMessage, Map.class);

        String userId = messageMap.get("message");
        System.out.println(userId);
        Optional<User> user = userRepository.findById(Long.parseLong(userId));
        if(user.isEmpty()){
            return;
        }
        List<CalendarMapper> mappers = calendarMapperRepository.findAllByUserAndCalendar_StatusIsNot(user.get(),0);
        List<String> calendarIds = new ArrayList<>();
        for (CalendarMapper mapper : mappers) {
            String id = String.valueOf(mapper.getCalendar().getCalendarId());
            calendarIds.add(id);
        }

        // 사용자가 구독할 캘린더 목록을 구독 처리
        userCalendarSubscriptions.put(userId, calendarIds);

        // 해당 캘린더에 대한 구독을 추가 (캘린더별로 구독 설정)
        for (String id : calendarIds) {
            System.out.println(id);
            messagingTemplate.convertAndSend( "/topic/calendar/" + id, "{\"message\": \"Subscribed to calendar: " + id + "\"}");
        }
    }

    // 특정 사용자가 캘린더 구독을 취소
    @MessageMapping("/unsubscribe")
    public void unsubscribeFromCalendar(String userId, String calendarId) {
        List<String> subscriptions = userCalendarSubscriptions.get(userId);
        if (subscriptions != null) {
            subscriptions.remove(calendarId);
        }
        System.out.println(userId + " has unsubscribed from calendar " + calendarId);
    }
}

package com.backend.service;

import com.backend.dto.request.calendar.PostCalendarContentDto;
import com.backend.dto.request.calendar.PutCalendarContentsDto;
import com.backend.dto.response.calendar.GetCalendarContentNameDto;
import com.backend.dto.response.calendar.GetCalendarNameDto;
import com.backend.dto.response.calendar.GetCalendarsDto;
import com.backend.entity.calendar.Calendar;
import com.backend.entity.calendar.CalendarContent;
import com.backend.entity.user.User;
import com.backend.repository.CalendarContentRepository;
import com.backend.repository.CalendarRepository;
import com.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final CalendarContentRepository calendarContentRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> getCalendarName(Long id) {
        List<Calendar> calendars = calendarRepository.findAllByUserId(id);
        if(calendars.isEmpty()){
            return ResponseEntity.ok("등록된 캘린더가 없습니다...");
        }
        List<GetCalendarNameDto> dtos = calendars.stream().map(Calendar::toGetCalendarNameDto).toList();
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getCalendar() {
        Optional<Calendar> calendar = calendarRepository.findByUserIdAndStatus(9L,1);
        if(calendar.isEmpty()){
            return ResponseEntity.ok("캘린더가 존재하지 않습니다.");
        }
        List<CalendarContent> contents = calendar.get().getCalendarContents();
        if(contents.isEmpty()){
            return ResponseEntity.ok("일정이 없습니다.");
        }
        List<GetCalendarsDto> dtos = contents.stream().map(CalendarContent::toGetCalendarsDto).toList();
        System.out.println(dtos);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getCalendarByCalendarId(Long calendarId) {
        Optional<Calendar> calendar = calendarRepository.findByCalendarIdAndStatusIsNot(calendarId,0);
        if(calendar.isEmpty()){
            return ResponseEntity.ok("캘린더가 존재하지 않습니다.");
        }
        List<CalendarContent> contents = calendar.get().getCalendarContents();
        if(contents.isEmpty()){
            return ResponseEntity.ok("등록된 일정이 없습니다.");
        }
        List<GetCalendarsDto> dtos = contents.stream().map(CalendarContent::toGetCalendarsDto).toList();
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getCalendarContentToday() {
        Long userId = 9L;
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("로그인 정보가 일치하지 않습니다...");
        }
        List<CalendarContent> constents = calendarContentRepository.findAllByCalendar_UserAndCalendarStartDateEqualsAndCalendar_StatusIsNot(user.get(),LocalDate.now(),0);
        if(constents.isEmpty()){
            return ResponseEntity.ok("오늘 등록된 일정이 없습니다.");
        }

        List<GetCalendarContentNameDto> dtos = constents.stream().map(CalendarContent::toGetCalendarContentNameDto).toList();
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> postCalendarContent(PostCalendarContentDto dto) {
        Long userId = 9L;
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("로그인 정보가 일치하지 않습니다...");
        }

        Optional<Calendar> calendar = calendarRepository.findByCalendarId(dto.getCalendarId());
        if(calendar.isEmpty()){
            return ResponseEntity.badRequest().body("등록된 캘린더가 없습니다.");
        }

        CalendarContent calendarContent = CalendarContent.builder()
                .calendar(calendar.get())
                .alertStatus(dto.getAlert())
                .calendarEndDate(LocalDate.parse(dto.getEdate()))
                .calendarStartDate(LocalDate.parse(dto.getSdate()))
                .endTime(dto.getEtime())
                .startTime(dto.getStime())
                .name(dto.getTitle())
                .memo(dto.getMemo())
                .importance(dto.getImportance())
                .location(dto.getLocation())
                .status(1)
                .build();

        calendarContentRepository.save(calendarContent);

        return ResponseEntity.ok("등록이 완료되었습니다.");
    }

    public ResponseEntity<?> putCalendarContents(List<PutCalendarContentsDto> dtos) {
        Long userId = 9L;
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("로그인 정보가 일치하지않습니다...");
        }
        for(PutCalendarContentsDto dto : dtos){
            Optional<CalendarContent> content = calendarContentRepository.findByCalendarContentId(dto.getContentId());
            if(content.isEmpty()){
                return ResponseEntity.badRequest().body("일정 정보가 일치하지않습니다...");
            }
            content.get().putContents(dto);
        }

        return ResponseEntity.ok().body("수정이 완료되었습니다.");
    }
}

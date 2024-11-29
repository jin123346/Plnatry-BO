package com.backend.service;

import com.backend.dto.request.calendar.PostCalendarContentDto;
import com.backend.dto.request.calendar.PutCalendarContentsDto;
import com.backend.dto.response.calendar.GetCalendarContentNameDto;
import com.backend.dto.response.calendar.GetCalendarNameDto;
import com.backend.dto.response.calendar.GetCalendarsDto;
import com.backend.entity.calendar.Calendar;
import com.backend.entity.calendar.CalendarContent;
import com.backend.entity.calendar.CalendarMapper;
import com.backend.entity.user.User;
import com.backend.repository.calendar.CalendarContentRepository;
import com.backend.repository.calendar.CalendarMapperRepository;
import com.backend.repository.calendar.CalendarRepository;
import com.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final CalendarContentRepository calendarContentRepository;
    private final UserRepository userRepository;
    private final CalendarMapperRepository calendarMapperRepository;

    public ResponseEntity<?> getCalendarName(Long id) {
        Optional<User> user = userRepository.findById(9L);
        if(user.isEmpty()){
            return ResponseEntity.ok().body("로그인 정보가 일치하지 않습니다.");
        }
        List<CalendarMapper> mappers = calendarMapperRepository.findAllByUserAndCalendar_StatusIsNot(user.get(),0);

        List<Calendar> calendars = mappers.stream().map(CalendarMapper::getCalendar).toList();

        if(calendars.isEmpty()){
            return ResponseEntity.ok("등록된 캘린더가 없습니다...");
        }
        List<GetCalendarNameDto> dtos = calendars.stream().map(Calendar::toGetCalendarNameDto).toList();
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getCalendar() {
        Optional<User> user = userRepository.findById(9L);
        if(user.isEmpty()){
            return ResponseEntity.ok().body("로그인 정보가 일치하지 않습니다.");
        }
        Optional<CalendarMapper> mapper = calendarMapperRepository.findByUserAndCalendar_Status(user.get(),1);
        if(mapper.isEmpty()){
            return ResponseEntity.ok("등록된 캘린더가 존재하지 않습니다.");
        }

        Calendar calendar = mapper.get().getCalendar();

        List<CalendarContent> contents = calendar.getCalendarContents();
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

        List<CalendarMapper> calendarMappers = calendarMapperRepository.findAllByUserAndCalendar_StatusIsNot(user.get(),0);
        if(calendarMappers.isEmpty()){
            return ResponseEntity.ok().body("등록된 캘린더가 없습니다.");
        }
        List<Calendar> calendars = calendarMappers.stream().map(CalendarMapper::getCalendar).toList();
        System.out.println(calendars);
        List<CalendarContent> contents = new ArrayList<>();
        for(Calendar calendar : calendars){
            List<CalendarContent> smallContents = calendar.getCalendarContents().stream()
                    .filter(v -> {
                        LocalDateTime startDate = v.getCalendarStartDate();
                        LocalDateTime endDate = v.getCalendarEndDate().minusDays(1);
                        LocalDateTime now = LocalDateTime.now();
                        return (startDate.isEqual(now) || startDate.isBefore(now)) && (endDate.isEqual(now) || endDate.isAfter(now));
                    })
                    .collect(Collectors.toList());
            contents.addAll(smallContents);
        }

        if(contents.isEmpty()){
            return ResponseEntity.ok("오늘 등록된 일정이 없습니다.");
        }

        List<GetCalendarContentNameDto> dtos = contents.stream().map(CalendarContent::toGetCalendarContentNameDto).toList();
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
                .calendarEndDate(LocalDateTime.parse(dto.getEdate()))
                .calendarStartDate(LocalDateTime.parse(dto.getSdate()))
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

package com.backend.service;

import com.backend.dto.response.calendar.GetCalendarNameDto;
import com.backend.dto.response.calendar.GetCalendarsDto;
import com.backend.entity.calendar.Calendar;
import com.backend.entity.calendar.CalendarContent;
import com.backend.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public ResponseEntity<?> getCalendarName(Long id) {
        List<Calendar> calendars = calendarRepository.findAllByUserId(id);
        if(calendars.isEmpty()){
            return ResponseEntity.ok("등록된 캘린더가 없습니다...");
        }
        List<GetCalendarNameDto> dtos = calendars.stream().map(Calendar::toGetCalendarNameDto).toList();
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getCalendar() {
        Optional<Calendar> calendar = calendarRepository.findByCalendarIdAndStatus(9L,1);
        if(calendar.isEmpty()){
            return ResponseEntity.ok("캘린더가 존재하지 않습니다.");
        }
        List<CalendarContent> contents = calendar.get().getCalendarContents();
        if(contents.isEmpty()){
            return ResponseEntity.ok("일정이 없습니다.");
        }
        List<GetCalendarsDto> dtos = contents.stream().map(CalendarContent::toGetCalendarsDto).toList();
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getCalendarByCalendarId(Long calendarId) {
        Optional<Calendar> calendar = calendarRepository.findByCalendarIdAndStatusIsNot(calendarId,0);
        if(calendar.isEmpty()){
            return ResponseEntity.ok("캘린더가 존재하지 않습니다.");
        }
        List<CalendarContent> contents = calendar.get().getCalendarContents();
        if(contents.isEmpty()){
            return ResponseEntity.ok("일정이 없습니다.");
        }
        List<GetCalendarsDto> dtos = contents.stream().map(CalendarContent::toGetCalendarsDto).toList();
        return ResponseEntity.ok(dtos);
    }
}

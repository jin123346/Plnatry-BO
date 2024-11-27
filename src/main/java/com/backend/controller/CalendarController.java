package com.backend.controller;

import com.backend.dto.request.calendar.PostCalendarContentDto;
import com.backend.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/calendar/content")
    public ResponseEntity<?> postCalendarContent (
            @RequestBody PostCalendarContentDto dto
            ) {
        System.out.println(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/calendar/name")
    public ResponseEntity<?> getCalendarName (
            @RequestParam Long id
    ){
        ResponseEntity<?> response = calendarService.getCalendarName(id);
        return response;
    }

    @GetMapping("/calendar")
    public ResponseEntity<?> getCalendar (
            @RequestParam(value = "calendarId",defaultValue = "0") Long calendarId
    ){
        if(calendarId==0){
            ResponseEntity<?> response = calendarService.getCalendar();
            return ResponseEntity.ok().body(response);
        } else {
            ResponseEntity<?> response = calendarService.getCalendarByCalendarId(calendarId);
        }

        return ResponseEntity.ok().build();
    }
}

package com.backend.controller;

import com.backend.dto.request.calendar.PostCalendarContentDto;
import com.backend.dto.request.calendar.PutCalendarContentsDto;
import com.backend.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        ResponseEntity<?> response = calendarService.postCalendarContent(dto);
        return response;
    }

    @GetMapping("/calendar/content/name/today")
    public ResponseEntity<?> getCalendarContentToday () {
        ResponseEntity<?> response = calendarService.getCalendarContentToday();
        return response;
    }

    @GetMapping("/calendar/content/name/morning")
    public ResponseEntity<?> getCalendarContentMorning (
            @RequestParam String today
    ) {
        ResponseEntity<?> response = calendarService.getCalendarContentMorning(today);
        return response;
    }

    @GetMapping("/calendar/content/name/afternoon")
    public ResponseEntity<?> getCalendarContentAfternoon (
            @RequestParam String today
    ) {
        ResponseEntity<?> response = calendarService.getCalendarContentAfternoon(today);
        return response;
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
            return response;
        } else {
            ResponseEntity<?> response = calendarService.getCalendarByCalendarId(calendarId);
            return response;
        }
    }

    @PutMapping("/calendar/contents")
    public ResponseEntity<?> putCalendarContents (
            @RequestBody List<PutCalendarContentsDto> dtos
    ){
        System.out.println(dtos.get(0).getStartDate());
        ResponseEntity<?> response = calendarService.putCalendarContents(dtos);
        return response;
    }

    @DeleteMapping("/calendar/content")
    public ResponseEntity<?> deleteCalendarContent (
        @RequestParam Long id
    ){
        ResponseEntity<?> response = calendarService.deleteCalendarContent(id);
        return response;
    }
}

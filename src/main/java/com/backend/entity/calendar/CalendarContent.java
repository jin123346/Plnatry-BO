package com.backend.entity.calendar;

import com.backend.dto.response.calendar.GetCalendarsDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "calendar_content")
public class CalendarContent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_content_id")
    private Long calendarContentId;

    @Column(name = "calendar_content_status")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @Column(name = "calendar_content_start_time")
    private String startTime;

    @Column(name = "calendar_content_end_time")
    private String endTime;

    @Column(name = "calendar_content_start_date")
    private LocalDate calendarStartDate;

    @Column(name = "calendar_content_end_date")
    private LocalDate calendarEndDate;

    @Column(name = "calendar_content_name")
    private String name;  // 일정 이름

    @Column(name = "calendar_content_memo")
    private String memo; // 일정 내용

    @Column(name = "calendar_content_alert_status")
    private int alertStatus; // 알람여부

    @Column(name = "calendar_content_location")
    private String location; // 장소지정

    @Column(name = "calendar_content_importance")
    private int importance; // 중요도

    public GetCalendarsDto toGetCalendarsDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sdate = formatter.format(calendarStartDate);
        return GetCalendarsDto.builder()
                .title(name)
                .id(calendarContentId)
                .date(sdate)
                .build();
    }

}

package com.backend.entity.calendar;

import com.backend.dto.response.calendar.GetCalendarNameDto;
import com.backend.dto.response.calendar.GetCalendarsDto;
import com.backend.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name = "calendar")
public class Calendar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long calendarId;

    @Column(name = "calendar_status")
    private int status;                     // 0 삭제 1 메인캘린더 2 서브캘린더

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "calendar_name")
    private String name;

    @OneToMany(mappedBy = "calendar",fetch = FetchType.EAGER)
    private List<CalendarContent> calendarContents = new ArrayList<>();

    public GetCalendarNameDto toGetCalendarNameDto() {
        return GetCalendarNameDto.builder()
                .id(calendarId)
                .name(name)
                .build();
    }

}

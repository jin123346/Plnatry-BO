package com.backend.dto.response.calendar;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class GetCalendarsDto {
    private Long id;
    private String title;
    private String date;
}

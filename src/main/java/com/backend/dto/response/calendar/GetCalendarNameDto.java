package com.backend.dto.response.calendar;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class GetCalendarNameDto {
    private Long id;
    private String name;
}

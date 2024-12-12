package com.backend.dto.response.page;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class MessagePageDto {
    private Object sendData;
    private String selectId;
    private Long userId;
    private String title;
}

package com.backend.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class AuthenticateDto {
    private String uid;
    private String role;
}

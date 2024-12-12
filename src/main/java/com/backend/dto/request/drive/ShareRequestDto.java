package com.backend.dto.request.drive;


import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareRequestDto {
    private String type; // "personal", "SelectedDepartment", or "department"
    private List<ShareEntity> users; // List of users with their details
    // For department sharing
    private List<ShareEntity> departments;

    @Data
    public static class ShareEntity {
        private Long id;
        private String permission;
    }


}

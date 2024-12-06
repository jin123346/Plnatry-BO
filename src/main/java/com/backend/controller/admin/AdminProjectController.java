package com.backend.controller.admin;

import com.backend.dto.response.admin.group.GetGroupDto;
import com.backend.dto.response.admin.project.GetProjectLeaderDto;
import com.backend.dto.response.user.GetUsersAllDto;
import com.backend.entity.project.Project;
import com.backend.service.GroupService;
import com.backend.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminProjectController {

    private final GroupService groupService;
    private final ProjectService projectService;

    @GetMapping("/project")
    public ResponseEntity<?> getAdminProject(
        @RequestParam String group,
        HttpServletRequest req
    ) {
        Map<String, Object> map = new HashMap<>();
        String company = "1246857";
        ResponseEntity<?> response = projectService.getLeaderInfo(group,company);
        GetProjectLeaderDto leaderDto = (GetProjectLeaderDto) response.getBody();
        map.put("leader", leaderDto);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/project/leader")
    public ResponseEntity<?> getAdminProjectLeader(
            @RequestParam Long id
    ){
        ResponseEntity<?> response = projectService.getLeaderDetail(id);
        return response;
    }
}

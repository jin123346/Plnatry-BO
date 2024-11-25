package com.backend.controller;

import com.backend.dto.request.PostDepartmentReqDto;
import com.backend.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    private final GroupService groupService;

    public DepartmentController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/department")
    public ResponseEntity<?> getDepartment() {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/department")
    public ResponseEntity<?> postDepartment(
            @RequestBody PostDepartmentReqDto dto
            ) {
        ResponseEntity<?> response = groupService.postDepartment(dto);
        return response;
    }
}

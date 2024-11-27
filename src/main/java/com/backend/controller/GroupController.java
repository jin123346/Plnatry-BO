package com.backend.controller;

import com.backend.dto.request.PostDepartmentReqDto;
import com.backend.dto.response.GetAdminUsersRespDto;
import com.backend.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/departments")
    public ResponseEntity<?> getDepartments() {
        ResponseEntity<?> response = groupService.getDepartments();
        return response;
    }

    @GetMapping("/teams")
    public ResponseEntity<?> getTeams() {
        ResponseEntity<?> response = groupService.getTeams();
        return response;
    }

    @PostMapping("/department")
    public ResponseEntity<?> postDepartment(
            @RequestBody PostDepartmentReqDto dto
            ) {
        ResponseEntity<?> response = groupService.postDepartment(dto);
        return response;
    }

    @PostMapping("/team")
    public ResponseEntity<?> postTeam(
            @RequestBody PostDepartmentReqDto dto
    ) {
        ResponseEntity<?> response = groupService.postTeam(dto);
        return response;
    }

    @GetMapping("/group/leader")
    public ResponseEntity<?> getLeader(
            @RequestParam(value = "team",defaultValue = "") String team
    ) {
        ResponseEntity<?> response = groupService.getLeader(team);
        return response;
    }

    @PatchMapping("/group/leader")
    public ResponseEntity<?> patchLeader(
            @RequestParam Long id,
            @RequestParam String name
            ){
        ResponseEntity<?> response = groupService.patchLeader(id,name);
        return response;
    }

    @GetMapping("/group/users")
    public ResponseEntity<?> getUsers(
            @RequestParam(value = "team",defaultValue = "") String team
    ){
        ResponseEntity<?> response = groupService.getGroupMembers(team);
        return response;
    }

    @GetMapping("/group/users/detail")
    public ResponseEntity<?> getUsersDtail(
            @RequestParam(value = "team",defaultValue = "") String team
    ){
        ResponseEntity<?> response = groupService.getGroupMembersDetail(team);
        return response;
    }

    @GetMapping("/group/users/approval")
    public ResponseEntity<?> getUsersApproval(){
        ResponseEntity<?> response = groupService.getGroupMembersApproval();
        return response;
    }

    @PatchMapping("/group/users")
    public ResponseEntity<?> patchUsers(
            @RequestParam List<Long> ids,
            @RequestParam String team
            ){
        System.out.println(ids);
        ResponseEntity<?> response = groupService.patchGroupMembers(ids,team);
        return response;
    }

    @PatchMapping("/group")
    public ResponseEntity<?> patchGroup(
            @RequestParam String name,
            @RequestParam String update
    ){
        ResponseEntity<?> response = groupService.patchGroupName(name,update);
        return response;
    }


}

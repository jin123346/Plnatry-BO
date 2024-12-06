package com.backend.controller;

import com.backend.dto.request.project.PostProjectDTO;
import com.backend.dto.response.project.GetProjectColumnDTO;
import com.backend.dto.response.project.GetProjectDTO;
import com.backend.dto.response.project.GetProjectTaskDTO;
import com.backend.entity.project.Project;
import com.backend.entity.project.ProjectColumn;
import com.backend.entity.project.ProjectTask;
import com.backend.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/project") // 프로젝트 생성
    public ResponseEntity<?> createProject(@RequestBody PostProjectDTO dto, HttpServletRequest request) {
        String ownerUid = (String) request.getAttribute("uid");
        Project savedProject = projectService.createProject(dto, ownerUid);
        return ResponseEntity.ok().body(savedProject);
    }

    @GetMapping("/projects") // 프로젝트 목록 출력
    public ResponseEntity<?> readProjectList(HttpServletRequest request) {
        String ownerUid = (String) request.getAttribute("uid");
        Map<String,Object> map = projectService.getAllProjects(ownerUid);
        return ResponseEntity.ok().body(map);
    }

    @GetMapping("/project/{id}") // 프로젝트 페이지 출력
    public ResponseEntity<?> readProject(@PathVariable Long id) {
        GetProjectDTO dto = projectService.getProject(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/project/{id}") // 프로젝트 컬럼 생성
    public ResponseEntity<?> createColumn(@PathVariable Long id, @RequestBody GetProjectColumnDTO dto) {
        ProjectColumn column = projectService.addColumn(dto, id);
        return ResponseEntity.ok().body(column);
    }
    @RequestMapping(value = "/project/task", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> upsertTask(@RequestBody GetProjectTaskDTO dto) { // 태스크 생성, 수정
        log.info("GetProjectTaskDTO: {}",dto);
        ProjectTask task = projectService.saveTask(dto);
        return ResponseEntity.ok().body(task);
    }
    @PatchMapping("/project/{id}") // 프로젝트 컬럼 생성
    public ResponseEntity<?> updateCoworker(@PathVariable Long id, @RequestBody GetProjectDTO dto) {
        Project savedProject = projectService.updateCoworker(dto, id);
        return ResponseEntity.ok().body(savedProject);
    }

}

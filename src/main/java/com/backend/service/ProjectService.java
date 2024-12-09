package com.backend.service;

import com.backend.dto.request.project.PostProjectDTO;
import com.backend.dto.response.admin.project.GetProjectLeaderDetailDto;
import com.backend.dto.response.admin.project.GetProjectLeaderDto;
import com.backend.dto.response.admin.project.GetProjects;
import com.backend.dto.response.project.GetProjectColumnDTO;
import com.backend.dto.response.project.GetProjectDTO;
import com.backend.dto.response.project.GetProjectTaskDTO;
import com.backend.dto.response.user.GetUsersAllDto;
import com.backend.entity.calendar.Calendar;
import com.backend.entity.calendar.CalendarMapper;
import com.backend.entity.group.Group;
import com.backend.entity.group.GroupLeader;
import com.backend.entity.group.GroupMapper;
import com.backend.entity.project.Project;
import com.backend.entity.project.ProjectColumn;
import com.backend.entity.project.ProjectCoworker;
import com.backend.entity.project.ProjectTask;
import com.backend.entity.user.User;
import com.backend.repository.GroupLeaderRepository;
import com.backend.repository.GroupRepository;
import com.backend.repository.UserRepository;
import com.backend.repository.calendar.CalendarMapperRepository;
import com.backend.repository.project.ProjectColumnRepository;
import com.backend.repository.project.ProjectCoworkerRepository;
import com.backend.repository.project.ProjectRepository;
import com.backend.repository.project.ProjectTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/*

    이름 : 김주경
    날짜 : 2024/12/03
    작업내용 : 프로젝트 생성

    수정이력
        - 2024/12/04 김주경 - 코드 간편화, 프로젝트 불러오기

 */

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final ProjectCoworkerRepository coworkerRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupLeaderRepository groupLeaderRepository;
    private final CalendarMapperRepository calendarMapperRepository;
    private final ProjectColumnRepository columnRepository;
    private final ProjectTaskRepository taskRepository;

    public Project createProject(PostProjectDTO postDTO, String username) {

        List<GetUsersAllDto> userList = postDTO.getCoworkers();
        Project project = postDTO.toProject();

        userList.forEach(u -> project.addCoworker(ProjectCoworker.builder()
                .user(User.builder().id(u.getId()).build())
                .project(project)
                .isOwner(false)
                .build()));

        project.addCoworker(ProjectCoworker.builder()
                        .user(userService.getUserByuid(username))
                        .isOwner(true)
                        .build());

        return projectRepository.save(project);
    }

    public Map<String,Object> getAllProjects(String username) {
        Map<String,Object> map = new HashMap<>();

        List<ProjectCoworker> allProjects = coworkerRepository.findByUserAndProjectStatusIsNot(userService.getUserByuid(username), 0);

        Map<String, List<ProjectCoworker>> groupedByStatus = allProjects.stream()
                .collect(Collectors.groupingBy(pc -> {
                    int status = pc.getProject().getStatus();
                    if (status == 1) return "waiting";
                    else if (status == 2) return "inProgress";
                    else if (status == 3) return "completed";
                    else return "unknown";
                }));

        map.put("waiting", groupedByStatus.getOrDefault("waiting", Collections.emptyList()).stream().map(ProjectCoworker::toGetProjectListDTO).toList());
        map.put("inProgress", groupedByStatus.getOrDefault("inProgress", Collections.emptyList()).stream().map(ProjectCoworker::toGetProjectListDTO).toList());
        map.put("completed", groupedByStatus.getOrDefault("completed", Collections.emptyList()).stream().map(ProjectCoworker::toGetProjectListDTO).toList());
        map.put("count",allProjects.size());
        return map;
    }

    public GetProjectDTO getProject(Long projectId) {
        Optional<Project> optProject = projectRepository.findById(projectId);
        if (optProject.isPresent()) {
            Project project = optProject.get();
            return project.toGetProjectDTO();
        }
        return null;
    }

    public ResponseEntity<?> getLeaderInfo(String group, String company) {
        Optional<Group> optGroup = groupRepository.findByNameAndStatusIsNotAndCompany(group,0,company);
        if(optGroup.isEmpty()) {
            return ResponseEntity.badRequest().body("정보가 일치하지 않습니다...");
        }
        User leader = optGroup.get().getGroupLeader().getUser();
        List<Project> project = projectRepository.findAllByCoworkers_UserAndStatusIsNot(leader,0);
        GetProjectLeaderDto projectLeaderDto = GetProjectLeaderDto.builder()
                .email(leader.getEmail())
                .name(leader.getName())
                .level(leader.selectLevelString())
                .id(leader.getId())
                .build();

        if(project.isEmpty()){
            projectLeaderDto.setTitle("없음");
            projectLeaderDto.setType("없음");
            projectLeaderDto.setStatus("없음");
        } else {
            if (project.size() >= 2) {
                projectLeaderDto.setTitle(project.get(0).getTitle() + " 외 " + (project.size()-1) + "개");
            } else {
                projectLeaderDto.setTitle(project.get(0).getTitle());
            }
            projectLeaderDto.setType(project.get(0).selectType());
            projectLeaderDto.setStatus(project.get(0).selectStatus());
        }
        return ResponseEntity.ok(projectLeaderDto);
    }

    public ProjectColumn addColumn(GetProjectColumnDTO columnDTO, Long projectId) {
        return columnRepository.save(columnDTO.toEntityAddProject(projectId));
    }

    public ProjectTask saveTask(GetProjectTaskDTO taskDTO) {
        return taskRepository.save(taskDTO.toProjectTask());
    }

    public Project updateCoworker(GetProjectDTO dto, Long projectId) {
        Optional<Project> optProject = projectRepository.findById(projectId);
        if (optProject.isPresent()) {
            Project project = optProject.get();
            project.setCoworkers(dto.getCoworkersEntity());
            return projectRepository.save(project);
        }
        return null;
    }

    public ResponseEntity<?> getProjects(String company, String group) {
        Optional<Group> optGroup = groupRepository.findByNameAndStatusIsNotAndCompany(group,0,company);
        if(optGroup.isEmpty()) {
            return ResponseEntity.badRequest().body("정보가 일치하지 않습니다...");
        }

        List<GroupMapper> groupMappers = optGroup.get().getGroupMappers();
        List<User> users = groupMappers.stream().map(GroupMapper::getUser).toList();
        List<Project> projects = new ArrayList<>();
        for (User user : users) {
            List<Project> project = projectRepository.findAllByCoworkers_UserAndStatusIsNot(user,0);
            projects.addAll(project);
        }
        Set<Project> uniqueProjects = new HashSet<>(projects);
        projects = new ArrayList<>(uniqueProjects);

        List<GetProjects> dtos = projects.stream().map(Project::toGetProjects).toList();
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getAdminProjectColumns(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isEmpty()){
            return ResponseEntity.badRequest().body("프로젝트가 없습니다...");
        }
        List<ProjectColumn> columns = project.get().getColumns();

        System.out.println("============================222");
        return ResponseEntity.ok(columns);
    }
}

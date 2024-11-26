package com.backend.service;

import com.backend.dto.request.PostDepartmentReqDto;
import com.backend.dto.response.GetAdminSidebarGroupsRespDto;
import com.backend.dto.response.GetAdminUsersRespDto;
import com.backend.entity.group.GroupLeader;
import com.backend.entity.group.Group;
import com.backend.entity.group.GroupMapper;
import com.backend.entity.user.User;
import com.backend.repository.GroupLeaderRepository;
import com.backend.repository.GroupMapperRepository;
import com.backend.repository.GroupRepository;
import com.backend.repository.UserRepository;
import com.backend.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupLeaderRepository groupLeaderRepository;
    private final GroupMapperRepository groupMapperRepository;


    public ResponseEntity<?> postDepartment(PostDepartmentReqDto dto) {
        Optional<Group> department = groupRepository.findByName(dto.getName());

        if(department.isPresent()){
            return ResponseEntity.badRequest().body("Department already exists");
        }
        List<User> users = userRepository.findAllById(dto.getMembers());

        if(users.isEmpty()){
            return ResponseEntity.badRequest().body("No users found");
        }

        Optional<User> user = userRepository.findById(dto.getLeader());

        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("No Leader found");
        }

        Group newGroup = Group.builder()
                .name(dto.getName())
                .status(1)
                .type(0)
                .build();
        groupRepository.save(newGroup);
        List<GroupMapper> mappers = new ArrayList<>();
        users.forEach(v->{
            GroupMapper groupMapper = GroupMapper.builder()
                    .group(newGroup)
                    .user(v)
                    .build();
            mappers.add(groupMapper);
        });

        groupMapperRepository.saveAll(mappers);

        GroupLeader departmentLeader = GroupLeader.builder()
                .group(newGroup)
                .user(user.get())
                .build();
        groupLeaderRepository.save(departmentLeader);

        user.get().updateRole(Role.DEPARTMENT);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> postTeam(PostDepartmentReqDto dto) {
        Optional<Group> department = groupRepository.findByName(dto.getName());

        if(department.isPresent()){
            return ResponseEntity.badRequest().body("Department already exists");
        }
        List<User> users = userRepository.findAllById(dto.getMembers());

        if(users.isEmpty()){
            return ResponseEntity.badRequest().body("No users found");
        }

        Optional<User> user = userRepository.findById(dto.getLeader());

        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("No Leader found");
        }

        Group newGroup = Group.builder()
                .name(dto.getName())
                .status(1)
                .type(1)
                .build();
        groupRepository.save(newGroup);
        List<GroupMapper> mappers = new ArrayList<>();
        users.forEach(v->{
            GroupMapper groupMapper = GroupMapper.builder()
                    .group(newGroup)
                    .user(v)
                    .build();
            mappers.add(groupMapper);
        });

        groupMapperRepository.saveAll(mappers);

        GroupLeader departmentLeader = GroupLeader.builder()
                .group(newGroup)
                .user(user.get())
                .build();
        groupLeaderRepository.save(departmentLeader);

        user.get().updateRole(Role.TEAM);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getDepartments() {
        Map<String, Object> resp = new HashMap<>();
        List<Group> departments = groupRepository.findAllByTypeAndStatus(0,1);
        List<GetAdminSidebarGroupsRespDto> dtos = departments.stream().map(Group::toGetAdminSidebarGroupsRespDto).toList();
        resp.put("deps",dtos);
        resp.put("depCnt",dtos.size());
        return ResponseEntity.ok(resp);
    }

    public ResponseEntity<?> getTeams() {
        Map<String, Object> resp = new HashMap<>();
        List<Group> departments = groupRepository.findAllByTypeAndStatus(1,1);
        List<GetAdminSidebarGroupsRespDto> dtos = departments.stream().map(Group::toGetAdminSidebarGroupsRespDto).toList();
        resp.put("teams",dtos);
        resp.put("teamCnt",dtos.size());
        return ResponseEntity.ok(resp);
    }

    public ResponseEntity<?> getLeader(String team) {
        Optional<Group> group = groupRepository.findByName(team);
        if(group.isEmpty()){
            return ResponseEntity.badRequest().body("일치하는 그룹이 없습니다.");
        }
        GroupLeader leader = group.get().getGroupLeader();
        if(leader == null){
            return ResponseEntity.badRequest().body("그룹장이 없습니다.");
        }
        User user = leader.getUser();
        GetAdminUsersRespDto dto = user.toGetAdminUsersRespDto();
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> patchLeader(Long id, String name) {
        Optional<Group> group = groupRepository.findByName(name);
        if(group.isEmpty()){
            return ResponseEntity.badRequest().body("해당하는 그룹이 존재하지않습니다.");
        }
        GroupLeader leader = group.get().getGroupLeader();
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.badRequest().body("해당하는 유저가 존재하지않습니다.");
        }
        if(leader == null){
            GroupLeader newGroupLeader = GroupLeader.builder()
                    .group(group.get())
                    .user(user.get())
                    .build();
            groupLeaderRepository.save(newGroupLeader);
            user.get().updateRole(Role.TEAM);
        } else {
            User oldLeader = leader.getUser();
            oldLeader.updateRole(Role.WORKER);
            userRepository.save(oldLeader);
            user.get().updateRole(Role.TEAM);
            leader.patchLeader(user.get());
        }
        return ResponseEntity.ok("변경완료했습니다.");
    }

}

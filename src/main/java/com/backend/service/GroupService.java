package com.backend.service;

import com.backend.dto.request.PostDepartmentReqDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}

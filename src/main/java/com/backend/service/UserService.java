package com.backend.service;

import com.backend.dto.request.admin.user.PatchAdminUserApprovalDto;
import com.backend.dto.response.GetAdminUsersRespDto;
import com.backend.dto.response.user.GetUsersAllDto;
import com.backend.entity.group.Group;
import com.backend.entity.group.GroupMapper;
import com.backend.entity.user.User;
import com.backend.repository.GroupRepository;
import com.backend.repository.UserRepository;
import com.backend.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public List<GetAdminUsersRespDto> getUserNotTeamLeader() {
        List<User> users = userRepository.findAllByRole(Role.WORKER);
        return users.stream().map(User::toGetAdminUsersRespDto).toList();
    }

    public ResponseEntity<?> patchUserApproval(PatchAdminUserApprovalDto dto) {
        Optional<User> user = userRepository.findById(dto.getUserId());
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("해당 유저가 회원가입을 취소했습니다.");
        }
        user.get().patchUserApproval(dto);

        return ResponseEntity.ok().body("승인처리하였습니다.");
    }


    public User getUserByuid(String uid){
        Optional<User> user = userRepository.findByUid(uid);
        if(user.isEmpty()){
            return null;
        }

        User findUser = user.get();

        return findUser;
    }

    // 11.29 전규찬 전체 사용자 조회 기능 추가
    public List<GetAdminUsersRespDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::toGetAdminUsersRespDto).toList();
    }

    public Page<GetUsersAllDto> getUsersAll(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<User> users = userRepository.findAllByCompanyAndStatusIsNot("1246857",0,pageable);
        Page<GetUsersAllDto> dtos = users.map(User::toGetUsersAllDto);
        return dtos;
    }

    public Page<GetUsersAllDto> getUsersAllByKeyword(int page,String keyword) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<User> users = userRepository.findAllByCompanyAndNameContainingAndStatusIsNot("1246857",keyword,0,pageable);
        Page<GetUsersAllDto> dtos = users.map(User::toGetUsersAllDto);
        return dtos;
    }

    public Page<GetUsersAllDto> getUsersAllByKeywordAndGroup(int page, String keyword, Long id) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<User> users = userRepository.findAllByCompanyAndNameContainingAndStatusIsNotAndGroupMappers_Group_Id("1246857",keyword,0,id,pageable);
        Optional<Group> group = groupRepository.findById(id);
        String groupName = group.get().getName();
        if(groupName.isEmpty()){
            return null;
        }
        Page<GetUsersAllDto> dtos = users.map(v->v.toGetUsersAllDto(groupName));
        return dtos;
    }

    public Page<GetUsersAllDto> getUsersAllByGroup(int page, Long id) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<User> users = userRepository.findAllByCompanyAndStatusIsNotAndGroupMappers_Group_Id("1246857",0,id,pageable);
        Optional<Group> group = groupRepository.findById(id);
        String groupName = group.get().getName();
        if(groupName.isEmpty()){
            return null;
        }
        Page<GetUsersAllDto> dtos = users.map(v->v.toGetUsersAllDto(groupName));
        return dtos;
    }
}

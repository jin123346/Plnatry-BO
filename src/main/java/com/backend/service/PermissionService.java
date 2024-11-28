package com.backend.service;


import com.backend.dto.response.UserDto;
import com.backend.entity.folder.Permission;
import com.backend.entity.user.User;
import com.backend.repository.PermissionRepository;
import com.backend.util.PermissionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public void createPermission(String folderId, User user) {
        log.info("forderId ,,,"+folderId);
        Permission permission = Permission.builder()
                .folderId(folderId)
                .permissions(PermissionType.FULL.getValue())
                .user(user)
                .build();

        permissionRepository.save(permission);
    }
}

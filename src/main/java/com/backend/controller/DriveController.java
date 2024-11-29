package com.backend.controller;


import com.backend.dto.request.drive.NewDriveRequest;
import com.backend.dto.response.UserDto;
import com.backend.dto.response.drive.FolderDto;
import com.backend.entity.folder.File;
import com.backend.entity.folder.Folder;
import com.backend.entity.user.User;
import com.backend.service.FolderService;
import com.backend.service.PermissionService;
import com.backend.service.SftpService;
import com.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:8010")
@RequestMapping("/api/drive")
@RequiredArgsConstructor
public class DriveController {


    private final FolderService folderService;
    private final UserService userService;
    private final PermissionService permissionService;
    private final SftpService sftpService;

    @PostMapping("/newDrive")
    public void createDrive(@RequestBody NewDriveRequest newDriveRequest) {
        log.info("New drive request: " + newDriveRequest);

        User currentUser = userService.getUserByuid(newDriveRequest.getOwner());
        Folder forFolder = folderService.getFolderName(currentUser.getUid());
        if(forFolder == null) {
            NewDriveRequest rootdrive = NewDriveRequest.builder()
                    .owner(newDriveRequest.getOwner())
                    .name(currentUser.getUid())
                    .driveMaster(currentUser.getUid())
                    .description(currentUser.getUid()+"의 드라이브")
                    .build();
            String rootId =folderService.createRootDrive(rootdrive);
            newDriveRequest.setParentId(rootId);
            permissionService.createPermission(rootId,currentUser);
        }else{
            newDriveRequest.setParentId(forFolder.getId());
        }

        //폴더생성
        String forderId = folderService.createDrive(newDriveRequest);

        //권한설정 저장
        permissionService.createPermission(forderId,currentUser);


    }


    @PostMapping("/newFolder")
    public void createFolder(@RequestBody NewDriveRequest newDriveRequest) {
        log.info("New drive request: " + newDriveRequest);

        FolderDto folderDto = folderService.getParentFolder(newDriveRequest.getParentId());
        newDriveRequest.setParentFolder(folderDto);
        newDriveRequest.setOrder(folderDto.getOrder()+1);
        User currentUser = userService.getUserByuid(newDriveRequest.getOwner());

        String folderId = folderService.createDrive(newDriveRequest);

        //권한설정 저장
        permissionService.createPermission(folderId,currentUser);


    }

    //사이드바  폴더 리스트 불러오기
    @GetMapping("/folders")
    public ResponseEntity getDriveList(@RequestParam String uid){

        if (uid == null || uid.isEmpty()) {
            return ResponseEntity.badRequest().body("UID is required.");
        }
        log.info("Get drive list  uid:"+uid);
        Folder rootFolder = folderService.getFolderName(uid);
        if (rootFolder == null) {
            return ResponseEntity.ok().body("No folders found.");
        }
        List<FolderDto> folderDtoList =  folderService.getFoldersByUid(uid, rootFolder.getId());
        log.info("folderLIst!!!!"+folderDtoList);

        return ResponseEntity.ok().body(folderDtoList);

    }


    //각 폴더의 컨텐츠 가져오기
    @GetMapping("/folder-contents")
    public ResponseEntity<Map<String, Object>> getFolderContents(@RequestParam String folderId,@RequestParam String ownerId){
        Map<String,Object> response = new HashMap<>();
        List<FolderDto> subFolders = folderService.getSubFolders(ownerId,folderId);
        response.put("subFolders", subFolders);

        log.info("subFolders:"+subFolders);


        return ResponseEntity.ok().body(response);

    }

    //폴더 이름 바꾸기
    @PutMapping("/folder/{text}/rename")
    public ResponseEntity renameFolder(@RequestParam String newFolderName,@PathVariable String text){
        log.info("Rename folder name:"+newFolderName);

        folderService.updateFolder(text, newFolderName);

        return null;
    }
}

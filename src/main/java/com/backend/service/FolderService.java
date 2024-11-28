package com.backend.service;


import com.backend.dto.request.drive.NewDriveRequest;
import com.backend.dto.response.drive.FolderDto;
import com.backend.entity.folder.Folder;
import com.backend.entity.folder.Permission;
import com.backend.entity.user.User;
import com.backend.repository.FolderMogoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class FolderService {

    private static final String BASE_UPLOAD_DIR = "/data/uploads/";
    private final SftpService sftpService;
    private final UserService userService;
    private final FolderMogoRepository folderMogoRepository;
    private String fileServerUrl = "http://43.202.45.49:90/local/upload/create-folder";


    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    public void makeDir(String folderName){

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = fileServerUrl;

        // 요청 헤더와 바디 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody = "{\"folderName\":\"" + folderName + "\"}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        // API 호출
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            File folder = new File(BASE_UPLOAD_DIR+folderName);

            if (!folder.exists()) {
                if (folder.mkdirs()) {
                    System.out.println("Folder created successfully"+ folderName);

                } else {
                    System.out.println("Failed to create folder"+ folderName);


                }
            } else {
                System.out.println("Folder already exists"+ folderName);

            }
            System.out.println("폴더 생성 성공: " + folderName);
        } else {
            System.err.println("폴더 생성 실패: " + response.getStatusCode());
        }
    }


    public String createDrive(NewDriveRequest request){


        String uid = request.getOwner();

        String makeDrivePath =  sftpService.createFolder(request.getName(),uid);

        log.info("결과!!!!"+makeDrivePath);

        if(makeDrivePath != null){
           Folder folder =  Folder.builder()
                   .name(request.getName())
                   .order(0)
                   .parentId(request.getParentId())
                   .path(makeDrivePath)
                   .ownerId(uid)
                   .description(request.getDescription())
                   .status(0)
                   .isShared(request.getIsShared())
                   .linkSharing(request.getLinkSharing())
                   .updatedAt(LocalDateTime.now())
                   .build();

           Folder savedFolder =  folderMogoRepository.save(folder);

           return savedFolder.getId();

        }
        return null;

    }

    public String createRootDrive(NewDriveRequest request){

        String uid = request.getOwner();
        String makeDrivePath =  sftpService.createRootFolder(request.getName(),uid);

        log.info("결과!!!!"+makeDrivePath);

        if(makeDrivePath != null){
            Folder folder =  Folder.builder()
                    .name(request.getName())
                    .order(0)
                    .parentId(null)
                    .path(makeDrivePath)
                    .ownerId(uid)
                    .description(request.getDescription())
                    .status(0)
                    .isShared(request.getIsShared())
                    .linkSharing(request.getLinkSharing())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Folder savedFolder =  folderMogoRepository.save(folder);

            return savedFolder.getId();

        }
        return null;

    }


    public List<FolderDto> getFoldersByUid(String uid,String parentId){
        List<Folder> folders = folderMogoRepository.findByOwnerIdAndAndParentId(uid,parentId);
            List<FolderDto> folderDtos = folders.stream().map(folder -> {
                FolderDto folderDto = FolderDto.builder()
                        .id(folder.getId())
                        .name(folder.getName())
                        .order(folder.getOrder())
                        .order(folder.getOrder())
                        .createdAt(folder.getCreatedAt())
                        .isShared(folder.getIsShared())
                        .isPinned(folder.getIsPinned())
                        .build();
                return folderDto;
            }).collect(Collectors.toList());
            return folderDtos;


    }


    public Folder getFolderName(String uid){
        return folderMogoRepository.findByName(uid);
    }


    public List<FolderDto> getSubFolders(String folderId){
        List<Folder> folders =folderMogoRepository.findByOwnerIdAndAndParentId(folderId,folderId);


        return folders.stream().map(Folder::toDTO).collect(Collectors.toList());
    }

}

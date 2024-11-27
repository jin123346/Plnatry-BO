package com.backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Service
@RequiredArgsConstructor
@Log4j2
public class FolderService {

    private static final String BASE_UPLOAD_DIR = "/data/uploads/";
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



}

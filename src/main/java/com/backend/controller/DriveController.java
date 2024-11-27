package com.backend.controller;


import com.backend.dto.request.drive.NewDriveRequest;
import com.backend.service.FolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@CrossOrigin(origins = "http://localhost:8010")
@RequestMapping("/api/drive")
@RequiredArgsConstructor
public class DriveController {


    private final FolderService folderService;

    @PostMapping("/newDrive")
    public void createDrive(@RequestBody NewDriveRequest newDriveRequest) {
        log.info("New drive request: " + newDriveRequest);

        folderService.createDrive(newDriveRequest);



    }
}

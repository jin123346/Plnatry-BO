package com.backend.controller;

import com.backend.dto.request.drive.ShareRequestDto;
import com.backend.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api")
public class ShareController {


    private final ShareService shareService;

    @PostMapping("/share/drive/{type}/{id}")
    public ResponseEntity shareDepartments(@RequestBody ShareRequestDto request,@PathVariable String type,@PathVariable String id) {
    log.info("부서 공유 들어옴" + request+"타입@2"+ request.getType());
    String sharetype = request.getType();

    if(sharetype.equals("personal")) {

        }else if(sharetype.equals("department")){
           shareService.sharedDepartment(request,type,id);
            return ResponseEntity.ok().body("success");

        }
    return null;
    }
}

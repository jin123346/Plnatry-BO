package com.backend.controller;

import com.backend.dto.request.page.PageDto;
import com.backend.service.mongoDB.PageService;
import com.backend.util.PermissionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/page")
@Log4j2
@RequiredArgsConstructor
public class PageController {


    private final PageService pageService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody PageDto pageDto){
        log.info("여기1!!!"+pageDto);
        log.info("Received PageDto: " + pageDto);
        log.info("Title: " + pageDto.getTitle());
        log.info("Content: " + pageDto.getContent());
        log.info("OwnerUid: " + pageDto.getOwnerUid());

        Map<String,Object> response = new HashMap<>();

        String Id = pageService.save(pageDto);
        response.put("id",Id);


      return ResponseEntity.ok().body(response);
    }

    @GetMapping("/view")
    public ResponseEntity view (@RequestParam String id){
        Map<String,Object> map = new HashMap<>();
        PageDto pageDto = pageService.findById(id);

        map.put("id",id);
        map.put("pageDto",pageDto);

        return ResponseEntity.ok(pageDto);
    }
}

package com.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {

    @GetMapping(value = {"/","/index"})
    public ResponseEntity<?> index() {

        return ResponseEntity.ok("SU");
    }

}

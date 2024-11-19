package com.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value = {"/","/index"})
    public ResponseEntity<?> index() {

        return ResponseEntity.ok("SU");
    }
}

package com.backend.controller;

import com.backend.dto.request.LoginDto;
import com.backend.dto.response.GetAdminUsersRespDto;
import com.backend.entity.user.User;
import com.backend.repository.UserRepository;
import com.backend.service.UserService;
import com.backend.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8010")
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity<?> login(
            @RequestBody LoginDto dto
    ){
        Optional<User> user = userRepository.findByUid(dto.getUid());
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(passwordEncoder.matches(dto.getPwd(), user.get().getPwd())){
            String jwts = tokenProvider.createToken(dto.getUid(),user.get().getRole().toString());
            Map<String, Object> response = new HashMap<>();
            response.put("token",jwts);
            response.put("role",user.get().getRole());
            return ResponseEntity.ok().body(response);
        }

        return ResponseEntity.ok().build();
    }


    @GetMapping("/users")
    public ResponseEntity<?> getUser(){
        List<GetAdminUsersRespDto> users = userService.getUserNotTeamLeader();
        System.out.println(users);
        return ResponseEntity.ok(users);
    }
}

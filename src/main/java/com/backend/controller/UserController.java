package com.backend.controller;

import com.backend.dto.request.LoginDto;
import com.backend.entity.User;
import com.backend.repository.UserRepository;
import com.backend.util.JwtTokenProvider;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8010")
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginDto dto
    ){
        Optional<User> user = userRepository.findByUid(dto.getUid());
        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(passwordEncoder.matches(dto.getPwd(), user.get().getPwd())){
            String jwts = tokenProvider.createToken(dto.getUid());
            System.out.println(jwts);
            return ResponseEntity.ok().body(jwts);
        }

        return ResponseEntity.ok().build();
    }
}

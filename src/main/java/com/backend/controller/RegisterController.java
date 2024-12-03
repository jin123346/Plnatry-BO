package com.backend.controller;

import com.backend.dto.request.user.PaymentInfoDTO;
import com.backend.dto.request.user.PostUserRegisterDTO;
import com.backend.dto.response.user.TermsDTO;
import com.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8010")
@RequiredArgsConstructor
@Log4j2
public class RegisterController {

    private final UserService userService;

    @GetMapping("/terms")
    public ResponseEntity<?> termsList(){
        List<TermsDTO> termsDTOS = userService.getTermsAll();
        log.info("텀즈 컨트롤러 접속"+termsDTOS);
        if(termsDTOS.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(termsDTOS);
        }
    }

    @PostMapping("/sendMail")
    public void sendMail(@RequestBody String receiver){
        log.info("이메일 전송 컨트롤러 "+receiver);
        userService.sendEmailCode(receiver);

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(PostUserRegisterDTO dto){

        PaymentInfoDTO paymentInfoDTO = dto.getPaymentInfo();
        paymentInfoDTO.setActiveStatus(1);
        Long paymentId = userService.insertPayment(paymentInfoDTO);

        dto.setPaymentId(paymentId);
        userService.insertUser(dto);

        return null;
    }
}

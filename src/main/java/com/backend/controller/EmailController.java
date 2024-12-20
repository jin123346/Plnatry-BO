package com.backend.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import com.backend.dto.request.email.*;
import com.backend.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(
   origins = {"http://localhost:8010", "http://13.124.94.213:90"}, 
   allowCredentials = "true", 
   allowedHeaders = "*", 
   exposedHeaders = "*",
   methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}
)
public class EmailController {
    @Autowired
   private EmailService emailService;
    @PostMapping("/send-auto-reply")
   public ResponseEntity<?> sendAutoReply(@RequestBody Map<String, String> request) {
       try {
           String email = request.get("email");
           if (email == null || email.trim().isEmpty()) {
               return ResponseEntity.badRequest()
                       .body(Map.of("message", "이메일 주소는 필수 항목입니다."));
           }
            emailService.sendAutoReplyEmail(email);
           return ResponseEntity.ok()
                   .body(Map.of("message", "자동 응답 이메일이 성공적으로 전송되었습니다."));
       } catch (Exception e) {
           log.error("자동 응답 이메일 전송 실패", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(Map.of("message", "자동 응답 이메일 전송에 실패했습니다: " + e.getMessage()));
       }
   }
    @PostMapping("/send-qna")
   public ResponseEntity<?> sendQnaEmail(@ModelAttribute @Validated QnaRequestDto request) {
       try {
           log.info("QNA 이메일 요청 수신: {}", request);
           emailService.sendQnaEmail(request);
           emailService.sendAutoReplyEmail(request.getEmail());
           return ResponseEntity.ok()
                   .body(Map.of("message", "문의가 성공적으로 전송되었습니다."));
       } catch (Exception e) {
           log.error("QNA 이메일 전송 실패", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(Map.of("message", "문의 전송에 실패했습니다: " + e.getMessage()));
       }
   }
    @PostMapping("/send-cancellation")
   public ResponseEntity<?> sendCancellationEmail(@RequestBody @Validated CancellationRequestDto request) {
       try {
           log.info("취소/반품 이메일 요청 수신: {}", request);
           emailService.sendCancellationEmail(request);
           emailService.sendAutoReplyEmail(request.getEmail());
           return ResponseEntity.ok()
                   .body(Map.of("message", "문의가 성공적으로 전송되었습니다."));
       } catch (Exception e) {
           log.error("취소/반품 이메일 전송 실패", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(Map.of("message", "문의 전송에 실패했습니다: " + e.getMessage()));
       }
   }
    @PostMapping("/send-product-service")
   public ResponseEntity<?> sendProductServiceEmail(@RequestBody @Validated ProductServiceRequestDto request) {
       try {
           log.info("제품/서비스 이메일 요청 수신: {}", request);
           emailService.sendProductServiceEmail(request);
           emailService.sendAutoReplyEmail(request.getEmail());
           return ResponseEntity.ok()
                   .body(Map.of("message", "문의가 성공적으로 전송되었습니다."));
       } catch (Exception e) {
           log.error("제품/서비스 이메일 전송 실패", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(Map.of("message", "문의 전송에 실패했습니다: " + e.getMessage()));
       }
   }
    @PostMapping("/send-payment")
   public ResponseEntity<?> sendPaymentEmail(@RequestBody @Validated PaymentRequestDto request) {
       try {
           log.info("결제 이메일 요청 수신: {}", request);
           
           if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
               return ResponseEntity.badRequest()
                       .body(Map.of("message", "이메일 주소는 필수 항목입니다."));
           }
            emailService.sendPaymentEmail(request);
           emailService.sendAutoReplyEmail(request.getEmail());
           return ResponseEntity.ok()
                   .body(Map.of("message", "문의가 성공적으로 전송되었습니다."));
       } catch (Exception e) {
           log.error("결제 이메일 전송 실패", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(Map.of("message", "문의 전송에 실패했습니다: " + e.getMessage()));
       }
   }
}
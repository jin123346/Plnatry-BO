package com.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backend.dto.request.email.QnaRequestDto;
import com.backend.dto.request.email.CancellationRequestDto;
import com.backend.dto.request.email.ProductServiceRequestDto;
import com.backend.dto.request.email.PaymentRequestDto;

@Service
@Slf4j
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")  // application.yml에 설정된 이메일 주소
    private String fromEmail;

    @Value("${admin.email}")
    private String adminEmail; // 관리자 이메일 설정

    // 공통 이메일 전송 메서드
    private void sendEmail(String[] to, String subject, String content, MultipartFile attachment) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        if (attachment != null && !attachment.isEmpty()) {
            helper.addAttachment(attachment.getOriginalFilename(), attachment);
        }

        // 이메일 전송 시 로깅
        logger.info("이메일 전송 시작: {}", subject);

        emailSender.send(message);

        // 성공적인 이메일 전송 로깅
        logger.info("이메일 전송 성공: {}", subject);
    }

    // QNA 이메일 전송 메서드
    public void sendQnaEmail(QnaRequestDto request) {
        try {
            String subject = "[QNA 문의 접수 확인] " + request.getTitle();
            String content = String.format("""
                <h2>QNA 문의가 접수되었습니다</h2>
                <p>안녕하세요, %s님.</p>
                <p>문의하신 내용이 정상적으로 접수되었습니다.</p>
                <p>접수시간: %s</p>
                <hr>
                <p><strong>카테고리:</strong> %s</p>
                <p><strong>우선순위:</strong> %s</p>
                <p><strong>문의자명:</strong> %s</p>
                <p><strong>이메일:</strong> %s</p>
                <p><strong>문의내용:</strong></p>
                <p>%s</p>
                <hr>
                <p>빠른 시일 내에 답변 드리도록 하겠습니다.</p>
                """,
                    request.getName(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    request.getCategory(),
                    request.getPriority(),
                    request.getName(),
                    request.getEmail(),
                    request.getContent()
            );

            sendEmail(
                    new String[]{fromEmail, request.getEmail()},
                    subject,
                    content,
                    request.getAttachments()
            );

        } catch (Exception e) {
            logger.error("이메일 전송 실패. 에러 메시지: {}", e.getMessage());
            logger.error("상세 에러: ", e);
            sendErrorNotification(e);  // 실패 시 관리자에게 알림 전송
            throw new RuntimeException("이메일 전송에 실패했습니다.", e);
        }
    }

    // 자동 응답 이메일 보내기
    public void sendAutoReplyEmail(String userEmail) {
        String subject = "문의 접수 확인";
        String content = "<p>귀하의 문의가 접수되었습니다. 빠른 시일 내에 답변 드리겠습니다.</p>";

        // SimpleMailMessage를 사용하여 간단한 이메일 전송
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(userEmail);
        message.setSubject(subject);
        message.setText(content);

        try {
            emailSender.send(message);
            logger.info("자동 응답 이메일 전송 성공");
        } catch (Exception e) {
            logger.error("자동 응답 이메일 전송 실패: {}", e.getMessage());
            e.printStackTrace();  // 예외 처리
        }
    }

    // 취소/반품 이메일 전송 메서드
    public void sendCancellationEmail(CancellationRequestDto request) {
        try {
            String subject = "[취소/반품 문의] " + request.getTitle();
            String content = String.format("""
                <h2>취소/반품 문의가 접수되었습니다</h2>
                <p>안녕하세요, %s님.</p>
                <p>접수시간: %s</p>
                <hr>
                <p><strong>주문번호:</strong> %s</p>
                <p><strong>상품명:</strong> %s</p>
                <p><strong>반품사유:</strong> %s</p>
                <p><strong>문의자명:</strong> %s</p>
                <p><strong>이메일:</strong> %s</p>
                <p><strong>상세내용:</strong></p>
                <p>%s</p>
                <hr>
                <p>빠른 시일 내에 답변 드리도록 하겠습니다.</p>
                """,
                    request.getName(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    request.getOrderNumber(),
                    request.getProductName(),
                    request.getReturnReason(),
                    request.getName(),
                    request.getEmail(),
                    request.getContent()
            );

            sendEmail(
                    new String[]{fromEmail, request.getEmail()},
                    subject,
                    content,
                    null
            );

        } catch (Exception e) {
            logger.error("이메일 전송 실패. 에러 메시지: {}", e.getMessage());
            logger.error("상세 에러: ", e);
            sendErrorNotification(e);  // 실패 시 관리자에게 알림 전송
            throw new RuntimeException("이메일 전송에 실패했습니다.", e);
        }
    }

    // 제품/서비스 이메일 전송 메서드
    public void sendProductServiceEmail(ProductServiceRequestDto request) {
        try {
            String subject = "[제품/서비스 문의] " + request.getTitle();
            String content = String.format("""
                <h2>제품/서비스 문의가 접수되었습니다</h2>
                <p>안녕하세요, %s님.</p>
                <p>접수시간: %s</p>
                <hr>
                <p><strong>제품명:</strong> %s</p>
                <p><strong>제품유형:</strong> %s</p>
                <p><strong>서비스유형:</strong> %s</p>
                <p><strong>구매일자:</strong> %s</p>
                <p><strong>문의자명:</strong> %s</p>
                <p><strong>이메일:</strong> %s</p>
                <p><strong>상세내용:</strong></p>
                <p>%s</p>
                <hr>
                <p>빠른 시일 내에 답변 드리도록 하겠습니다.</p>
                """,
                    request.getName(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    request.getProductName(),
                    request.getProductType(),
                    request.getServiceType(),
                    request.getPurchaseDate(),
                    request.getName(),
                    request.getEmail(),
                    request.getContent()
            );

            sendEmail(
                    new String[]{fromEmail, request.getEmail()},
                    subject,
                    content,
                    null
            );

        } catch (Exception e) {
            logger.error("이메일 전송 실패. 에러 메시지: {}", e.getMessage());
            logger.error("상세 에러: ", e);
            sendErrorNotification(e);  // 실패 시 관리자에게 알림 전송
            throw new RuntimeException("이메일 전송에 실패했습니다.", e);
        }
    }

    // 결제 문의 이메일 전송 메서드
    public void sendPaymentEmail(PaymentRequestDto request) {
        try {
            String subject = "[결제 문의] " + (request.getTitle() != null ? request.getTitle() : "제목 없음");
            String content = String.format("""
                <h2>결제 문의가 접수되었습니다</h2>
                <p>안녕하세요, %s님.</p>
                <p>접수시간: %s</p>
                <hr>
                <p><strong>주문번호:</strong> %s</p>
                <p><strong>결제금액:</strong> %d원</p>
                <p><strong>결제방법:</strong> %s</p>
                <p><strong>결제일자:</strong> %s</p>
                <p><strong>문의유형:</strong> %s</p>
                <p><strong>문의내용:</strong></p>
                <p>%s</p>
                """,
                    request.getName() != null ? request.getName() : "고객",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    request.getOrderNumber(),
                    request.getPaymentAmount(),
                    request.getPaymentMethod(),
                    request.getPaymentDate(),
                    request.getInquiryType(),
                    request.getContent()
            );

            sendEmail(
                    new String[]{request.getEmail(), fromEmail},
                    subject,
                    content,
                    null
            );

        } catch (Exception e) {
            logger.error("이메일 전송 실패. 에러 메시지: {}", e.getMessage());
            logger.error("상세 에러: ", e);
            sendErrorNotification(e);  // 실패 시 관리자에게 알림 전송
            throw new RuntimeException("이메일 전송에 실패했습니다.", e);
        }
    }

    // 관리자에게 실패 알림 이메일 보내기
    private void sendErrorNotification(Exception e) {
        String subject = "이메일 전송 실패 알림";
        String content = String.format("""
            <h2>이메일 전송이 실패하였습니다.</h2>
            <p>에러 메시지: %s</p>
            <p>상세 에러:</p>
            <pre>%s</pre>
            <hr>
            <p>관리자님, 확인 부탁드립니다.</p>
            """, e.getMessage(), e.getStackTrace());

        try {
            sendEmail(new String[]{adminEmail}, subject, content, null);
        } catch (MessagingException ex) {
            logger.error("관리자 이메일 전송 실패. 에러 메시지: {}", ex.getMessage());
        }
    }
}

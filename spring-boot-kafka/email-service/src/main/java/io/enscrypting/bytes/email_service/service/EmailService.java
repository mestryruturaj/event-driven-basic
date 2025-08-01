package io.enscrypting.bytes.email_service.service;

import io.enscrypting.bytes.library.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(OrderEvent orderEvent, String toEmail, String fromEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(orderEvent.getEventType().name());
        message.setText(orderEvent.toString());

        javaMailSender.send(message);
        log.info("Email sent => {}", message);
    }

}

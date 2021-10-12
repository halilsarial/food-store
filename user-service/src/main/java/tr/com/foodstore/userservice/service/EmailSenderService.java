package tr.com.foodstore.userservice.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {
    void sendEmail(String subject, String content, String to);
}

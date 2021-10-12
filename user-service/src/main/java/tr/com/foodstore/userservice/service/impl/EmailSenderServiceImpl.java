package tr.com.foodstore.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tr.com.foodstore.userservice.service.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.mail.from}")
    private String from;

    @Autowired
    public EmailSenderServiceImpl() {
    }

    @Override
    public void sendEmail(String subject, String content, String to) {
        javaMailSender.send(createEmailMessage(subject, content, to));
    }

    private SimpleMailMessage createEmailMessage(String subject, String content, String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setFrom(from);
        mailMessage.setText(content);
        return mailMessage;
    }
}

package br.com.nordestech.email.service;

import br.com.nordestech.email.dto.EmailRequest;
import br.com.nordestech.email.dto.EmailResponse;
import br.com.nordestech.email.exception.EmailSendingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${SMTP_FROM}")
    private String from;

    public EmailResponse sendEmail(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();

        log.info("Convertendo EmailRequest para SimpleMailMessage");
        message.setFrom(from);
        message.setTo(emailRequest.to().toArray(String[]::new));
        message.setSubject(emailRequest.subject());
        message.setText(emailRequest.body());

        try {
            log.info("Enviando Email...");
            mailSender.send(message);

            return EmailResponse.builder()
                    .send(true)
                    .message("E-mail enviado com sucesso")
                    .build();
        } catch (MailException ex) {
            throw new EmailSendingException(ex);
        }
    }
}

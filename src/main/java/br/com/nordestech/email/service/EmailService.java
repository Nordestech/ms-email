package br.com.nordestech.email.service;

import br.com.nordestech.email.dto.request.EmailRequest;
import br.com.nordestech.email.dto.response.EmailHtmlResponse;
import br.com.nordestech.email.dto.response.EmailResponse;
import br.com.nordestech.email.exception.EmailSendingException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    public void sendHtml(
            EmailHtmlResponse emailHtml) {

        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(
                            message,
                            true,
                            "UTF-8"
                    );

            helper.setFrom(from);
            helper.setTo(emailHtml.to());
            helper.setSubject(emailHtml.subject());
            helper.setText(emailHtml.html(), true);

            helper.addInline(
                    "nordestech-logo",
                    new ClassPathResource("static/images/icone-nordestech.png"),
                    "image/png"
            );

            mailSender.send(message);

        } catch (MessagingException | MailException exception) {
            throw new EmailSendingException(
                    exception
            );
        }
    }
}

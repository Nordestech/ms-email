package br.com.nordestech.email.controller;

import br.com.nordestech.email.dto.request.EmailRequest;
import br.com.nordestech.email.dto.response.EmailResponse;
import br.com.nordestech.email.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Email Sender", description = "Endpoint para envio de e-mails via SMTP")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/new")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody @Valid EmailRequest emailRequest) {

        log.info("Request de e-mail recebida: {}", emailRequest);
        EmailResponse emailResponse = emailService.sendEmail(emailRequest);

        log.info("E-mail enviado com sucesso: {}", emailResponse);
        return ResponseEntity.status(HttpStatus.OK).body(emailResponse);

    }

}

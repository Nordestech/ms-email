package br.com.nordestech.email.port.output.consumer;

import br.com.nordestech.email.config.RabbitMQConfig;
import br.com.nordestech.email.dto.request.EmailHtmlRequest;
import br.com.nordestech.email.dto.response.EmailHtmlResponse;
import br.com.nordestech.email.service.EmailService;
import br.com.nordestech.email.service.EmailTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WelcomeEmailConsumer {

    private final EmailService emailService;
    private final EmailTemplateService templateService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_WELCOME_EMAIL)
    public void consume(EmailHtmlRequest emailHtml) {

        String html = templateService.renderWelcomeEmail(
            emailHtml.nomeCompleto()
        );

        EmailHtmlResponse emailHtmlResponse = EmailHtmlResponse
                .builder()
                .to(emailHtml.to())
                .subject(emailHtml.subject())
                .html(html)
                .build();

        emailService.sendHtml(emailHtmlResponse);
    }
}
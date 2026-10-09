package br.com.nordestech.email.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailTemplateService {

    private final SpringTemplateEngine templateEngine;

    public String renderWelcomeEmail(String name) {
        Context context = new Context();
        context.setVariable("name", name);

        return templateEngine.process(
            "emails/welcome",
            context
        );
    }
}
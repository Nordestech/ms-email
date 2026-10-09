package br.com.nordestech.email.dto.response;

import lombok.Builder;

@Builder
public record EmailHtmlResponse(

        String to,
        String subject,
        String html

) {
}

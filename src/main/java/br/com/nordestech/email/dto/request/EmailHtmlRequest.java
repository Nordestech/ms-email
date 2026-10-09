package br.com.nordestech.email.dto.request;

import lombok.Builder;

@Builder
public record EmailHtmlRequest(

        String to,
        String nomeCompleto,
        String subject

) {
}

package br.com.nordestech.email.dto.response;

import lombok.Builder;

@Builder
public record EmailResponse(

        Boolean send,

        String message

) {
}

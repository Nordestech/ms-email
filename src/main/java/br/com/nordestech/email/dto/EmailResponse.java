package br.com.nordestech.email.dto;

import lombok.Builder;

@Builder
public record EmailResponse(

        Boolean send,

        String message

) {
}

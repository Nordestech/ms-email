package br.com.nordestech.email.exception;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(
            Throwable cause
    ) {
        super(String.format("Erro no envio do email: '%s'", cause));
    }
}

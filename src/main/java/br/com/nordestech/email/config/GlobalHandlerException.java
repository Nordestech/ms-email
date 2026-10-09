package br.com.nordestech.email.config;

import br.com.nordestech.email.exception.EmailSendingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {


    /**
     * Exceptions de tratamento para o {@code ms-usuario}
     */

    @ExceptionHandler(EmailSendingException.class)
    public ResponseEntity<ProblemDetail> handleEmailSendingException(EmailSendingException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_GATEWAY,
                ex.getMessage()
        );

        problemDetail.setTitle("Erro no envio do email");

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(problemDetail);
    }

    /**
     * Exceptions de tratamento para o {@code @Valid}
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationErrors(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Dados inválidos"
        );

        problemDetail.setTitle("Erro de Validação");

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        problemDetail.setProperty("fields", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

}

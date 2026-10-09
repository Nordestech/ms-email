
package br.com.nordestech.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record EmailRequest(

        @NotEmpty(message = "A lista de e-mails 'to' é obrigatória")
        @Size(max = 50, message = "A lista pode conter até 50 e-mails")
        List<
                @NotBlank(message = "O e-mail não pode estar vazio")
                @Email(message = "E-mail inválido")
                        String
                > to,

        @NotBlank(message = "O subject é obrigatório")
        @Size(max = 200, message = "O subject pode ter até 200 caracteres")
        String subject,

        @NotBlank(message = "O body é obrigatório")
        @Size(max = 10000, message = "O body pode ter até 10000 caracteres")
        String body

) {
}

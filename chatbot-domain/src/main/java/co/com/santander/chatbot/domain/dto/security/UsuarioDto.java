package co.com.santander.chatbot.domain.dto.security;

import co.com.santander.chatbot.domain.validators.MailConstraint;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
    @MailConstraint(message = "Correo")
    @MandatoryConstraint(message = "Correo")
    private String correo;
    @MandatoryConstraint(message = "Password")
    private String contrasena;
}

package co.com.santander.chatbot.domain.dto.security;

import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
    @MandatoryConstraint(message = "Correo es obligatorio", min = 1, max = Integer.MAX_VALUE)
    private String correo;
    @MandatoryConstraint(message = "Correo es obligatorio", min = 1, max = Integer.MAX_VALUE)
    private String contrasena;
}

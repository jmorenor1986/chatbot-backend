package co.com.santander.chatbot.domain.dto.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
    private String correo;
    private String contrasena;
}

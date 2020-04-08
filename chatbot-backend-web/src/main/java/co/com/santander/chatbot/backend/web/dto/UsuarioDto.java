package co.com.santander.chatbot.backend.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class UsuarioDto {

    private String correo;
    private String contrasena;

}

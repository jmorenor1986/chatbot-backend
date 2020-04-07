package co.com.santander.chatbot.accesodatos.controller.input;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
public class UsuarioInput {
    private int telefono;
    @NotEmpty(message = "Cola identificación es obligatorio")
    private String colaIdentificacion;
}

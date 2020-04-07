package co.com.santander.chatbot.accesodatos.controller.input;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
public class UsuarioInput {
    @NotEmpty(message = "Telefono es obligatorio")
    private Long telefono;
    @NotEmpty(message = "Cola identificación es obligatorio")
    private String colaIdentificacion;
}

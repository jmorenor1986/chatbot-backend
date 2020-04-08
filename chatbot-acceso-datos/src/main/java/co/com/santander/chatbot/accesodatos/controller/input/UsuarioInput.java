package co.com.santander.chatbot.accesodatos.controller.input;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Builder
@Data
public class UsuarioInput {
    private BigInteger telefono;
    @NotEmpty(message = "Cola identificación es obligatorio")
    private String colaIdentificacion;
}

package co.com.santander.chatbot.domain.dto.security;

import co.com.santander.chatbot.domain.validators.MailConstraint;
import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
    @ApiModelProperty(
            notes = "Correo de aplicación que se desea conectar a la información del cliente",
            name = "Correo",
            dataType = "String",
            required = true,
            allowableValues = "{Formato correo example@mail.com}"
    )
    @MailConstraint(message = "Correo")
    @MandatoryConstraint(message = "Correo")
    private String correo;
    @ApiModelProperty(
            notes = "Contraseña asignada por el Banco al usuario",
            name = "Contraseña",
            dataType = "String",
            required = true,
            allowableValues = "Cadena de caracteres"
    )
    @MandatoryConstraint(message = "Password")
    private String contrasena;
}

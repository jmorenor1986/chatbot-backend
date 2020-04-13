package co.com.santander.chatbot.acceso.recursos.clients.core.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDto {
    private List<CreditoUsuarioDto> consultarCreditosUsuario;
}

package co.com.santander.chatbot.acceso.recursos.clients.core.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreditoUsuarioDto {
    private String numeroCredito;
    private String numeroVerificador;
    private int tipoCredito;
    private int banco;
    private String convenio;

}

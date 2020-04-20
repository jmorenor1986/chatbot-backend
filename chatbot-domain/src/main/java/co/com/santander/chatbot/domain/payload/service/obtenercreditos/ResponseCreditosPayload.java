package co.com.santander.chatbot.domain.payload.service.obtenercreditos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCreditosPayload {

    private String numeroCreditoOfuscado;
    private String numeroVerificador;
    private Long tipoCredito;
    private Long banco;
    private String convenio;

}

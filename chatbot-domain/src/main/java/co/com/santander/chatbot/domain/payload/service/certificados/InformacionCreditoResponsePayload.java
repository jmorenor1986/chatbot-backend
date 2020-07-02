package co.com.santander.chatbot.domain.payload.service.certificados;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InformacionCreditoResponsePayload {

    private String resultado;
    private String emailOfuscado;
    private String tipoCredito;
    private String numeroCreditoOfuscado;
    private String convenio;
    private String infoUnoR;
    private String infoDosR;
    private String infoTresR;
    private String idRespuesta;
    private String descripcionRespuesta;
}

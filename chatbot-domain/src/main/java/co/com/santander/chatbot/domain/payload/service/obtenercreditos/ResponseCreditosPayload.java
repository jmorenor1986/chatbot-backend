package co.com.santander.chatbot.domain.payload.service.obtenercreditos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String infoUnoR;
    private String infoDosR;
    private String infoTresR;
    @JsonIgnore
    private String estado;

}

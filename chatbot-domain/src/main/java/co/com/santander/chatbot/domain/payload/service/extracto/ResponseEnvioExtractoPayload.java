package co.com.santander.chatbot.domain.payload.service.extracto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ResponseEnvioExtractoPayload {

    private Boolean resultadoEnvio;
    private String emailOfuscado;
    private Integer tipoCredito;
    private String numeroCreditoOfuscado;
    private String convenio;
    private String infoUnoR;
    private String infoDosR;
    private String infoTresR;
    private Integer idRespuesta;
    private String descripcionRespuesta;

}

package co.com.santander.chatbot.domain.payload.service.obtenercreditos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseObtenerCreditosPayload {
    private Boolean resultadoConsulta;
    private Long idRespuesta;
    private String descripcionRespuesta;
    private String infoUnoR;
    private String infoDosR;
    private String infoTresR;

}

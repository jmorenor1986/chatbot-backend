package co.com.santander.chatbot.domain.payload.service.enlacePse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseEnlacePsePayload {

    private String resultadoOperacion;
    private String tipoCredito; //1 (Vehiculo) 2 (Consumo)
    private String enlace;
    private String valorPagar;
    private String idRespuesta;
    private String descripcionRespuesta;
}
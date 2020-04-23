package co.com.santander.chatbot.domain.payload;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ConsultaParametrosServicioPayload {
    private String servicio;
    private String canal;
    private Date fechaUltimaSolicitud;
}

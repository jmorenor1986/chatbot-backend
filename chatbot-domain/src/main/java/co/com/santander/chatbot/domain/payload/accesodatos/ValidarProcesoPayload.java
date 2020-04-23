package co.com.santander.chatbot.domain.payload.accesodatos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ValidarProcesoPayload {
    private String servicio;
    private String canal;
    private Date fechaUltimaSolicitud;

}

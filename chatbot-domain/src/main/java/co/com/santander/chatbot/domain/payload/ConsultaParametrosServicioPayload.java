package co.com.santander.chatbot.domain.dto.accesorecursos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsultaParametrosServicioDto {
    private String servicio;
    private String canal;
    private Date fechaUltimaSolicitud;
}

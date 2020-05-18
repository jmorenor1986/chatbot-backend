package co.com.santander.chatbot.domain.payload.accesodatos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class TerminosCondicionesPayload {

    private Long id;
    private Long telefono;
    private Date horaEnviadoTeminos;
    private Date horaOperacion;
    private Long operacion;

}

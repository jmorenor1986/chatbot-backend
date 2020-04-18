package co.com.santander.chatbot.domain.payload.accesodatos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor @AllArgsConstructor
public class InfoWhatsAppWSPayload {

    private Long id;

    private String numCreditoBanco;

    private String numeroIdentificacion;

    private Long numPeticionServicio;

    private Date FechaEnvio;

    private Long estado;
}

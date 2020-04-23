package co.com.santander.chatbot.domain.payload.accesodatos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoWhatsAppWSPayload {

    private String numCreditoBanco;

    private String numeroIdentificacion;

    private Long numPeticionServicio;

    private Date fechaEnvio;

    private Long estado;
}

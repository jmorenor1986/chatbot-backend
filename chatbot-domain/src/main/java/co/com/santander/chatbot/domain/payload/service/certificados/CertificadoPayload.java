package co.com.santander.chatbot.domain.payload.service.certificados;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CertificadoPayload {
    private String identificacion;
    private String numeroCredito;
    private Long numeroPeticion;
}

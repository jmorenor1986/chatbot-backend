package co.com.santander.chatbot.domain.payload.service.certificados;

import co.com.santander.chatbot.domain.validators.MandatoryConstraint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CertificadoPayload {
    @MandatoryConstraint(message = "Número de identificacion errado")
    private String identificacion;
    @MandatoryConstraint(message = "Número de credito errado")
    private String numeroCredito;
}

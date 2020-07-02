package co.com.santander.chatbot.domain.validators.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ACCEPTED)
public class ValidateStateCertificateException extends RuntimeException {
    @Getter
    private String email;
    @Getter
    private String numeroCredito;
    @Getter
    private String convenio;
    @Getter
    private Long minutos;
    @Getter
    private String idRespuesta;
    @Getter
    private String tipoCredito;

    public ValidateStateCertificateException(String message, String email, String numeroCredito, String convenio, Long minutos, String idRespuesta, String tipoCredito) {
        super(message);
        this.email = email;
        this.numeroCredito = numeroCredito;
        this.convenio = convenio;
        this.minutos = minutos;
        this.idRespuesta = idRespuesta;
        this.tipoCredito = tipoCredito;
    }

    public ValidateStateCertificateException(String message, Long minutos) {
        super(message);
        this.minutos = minutos;
    }
}

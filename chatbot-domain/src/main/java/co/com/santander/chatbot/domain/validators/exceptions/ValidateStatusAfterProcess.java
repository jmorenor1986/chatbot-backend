package co.com.santander.chatbot.domain.validators.exceptions;

import lombok.Getter;

public class ValidateStatusAfterProcess extends RuntimeException {
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

    public ValidateStatusAfterProcess(String message, String email, String numeroCredito, String convenio, Long minutos, String idRespuesta) {
        super(message);
        this.email = email;
        this.numeroCredito = numeroCredito;
        this.convenio = convenio;
        this.minutos = minutos;
        this.idRespuesta = idRespuesta;
    }
}

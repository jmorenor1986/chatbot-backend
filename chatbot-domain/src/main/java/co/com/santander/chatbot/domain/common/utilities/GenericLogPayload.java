package co.com.santander.chatbot.domain.common.utilities;

import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericLogPayload {

    private String token;
    private ServiciosEnum serviciosEnum;
    private String telefono;
    private String identificacion;
    private String credito;
    private String traza;
    private String request;
    private Long tipoOperacion;
}

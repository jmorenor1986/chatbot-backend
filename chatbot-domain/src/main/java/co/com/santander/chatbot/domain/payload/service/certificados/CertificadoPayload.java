package co.com.santander.chatbot.backend.web.common.aspect.validate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatosTest {
    private String identificacion;
    private String numeroCredito;
    private Long numeroPeticion;
}

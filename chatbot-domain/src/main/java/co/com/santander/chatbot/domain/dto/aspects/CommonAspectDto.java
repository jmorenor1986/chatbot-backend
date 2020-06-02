package co.com.santander.chatbot.domain.dto.aspects;

import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommonAspectDto {

    private String token;
    private ServiciosEnum servicioEnum;
    private Long numPeticionServicio;
    private String credito;
    private String identificacion;

}

package co.com.santander.chatbot.acceso.recursos.clients.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDto {
    private String telefono;
    private int tipoOperacion;
}

package co.com.santander.chatbot.domain.payload.service.extracto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class EnvioExtractoPayload {

    private String telefono;
    private String numeroCreditoOfuscado;
    private String numeroVerificador;
    private Integer idDocumentos;
    private Integer mes;
    private Integer vigencia;

}

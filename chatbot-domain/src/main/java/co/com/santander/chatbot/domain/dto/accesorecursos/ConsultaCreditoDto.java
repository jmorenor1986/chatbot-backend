package co.com.santander.chatbot.domain.dto.accesorecursos;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class ConsultaCreditoDto {
    private BigInteger telefono;
    private Integer tipoOperacion;
}

package co.com.santander.chatbot.domain.dto.accesorecursos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Credito {
    private String numeroCreditoOfuscado;
    private String numeroVerificador;
    private Integer tipoCredito;
    private Integer banco;
    private String convenio;
}

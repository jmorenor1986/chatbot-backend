package co.com.santander.chatbot.domain.payload.accesodatos.cliente;

import co.com.santander.chatbot.domain.enums.TipoCredito;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ClienteViewPayload {
    private Long id;
    private String nombreCliente;
    private String telefono;
    private String cedula;
    private String email;
    private String numerCredito;
    private String banco;
    private String estado;
    private String idProducto;
    private String idBanco;
    private String convenio;
    private Long valorPagar;
    private Long valorMora;
    private TipoCredito tipoCredito;
}

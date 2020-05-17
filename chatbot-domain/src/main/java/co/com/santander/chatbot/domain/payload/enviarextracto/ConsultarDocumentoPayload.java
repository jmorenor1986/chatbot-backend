package co.com.santander.chatbot.domain.payload.enviarextracto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultarDocumentoPayload {
    private String docId;
    private String fechaFin;
    private String fechaIni;
    private String folder;
    private String formatoConsulta;
    private String valorllave;
    private ProductoEnum producto;
}

package co.com.santander.chatbot.domain.payload.enviarextracto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultarDocumentosPayloadResponse {
    private String docId;
    private String formatoConsulta;
    private String folder;
    private List<IndicesPayloadResponse> indices;
}

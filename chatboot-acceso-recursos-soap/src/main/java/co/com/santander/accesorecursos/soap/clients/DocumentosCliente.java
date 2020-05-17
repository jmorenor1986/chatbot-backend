package co.com.santander.accesorecursos.soap.clients;

import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentosPayloadResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnviarMailDocumentoPayload;

import java.util.List;

public interface DocumentosCliente {
    List<ConsultarDocumentosPayloadResponse> consultarDocumentos(ConsultarDocumentoPayload consultarDocumentoPayload);

    String enviarMailDocumentoId(EnviarMailDocumentoPayload enviarMailDocumentoPayload);

}

package co.com.santander.accesorecursos.soap.clients;

import co.com.santander.accesorecursos.soap.resources.documentos.ConsultarDocumentosResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;

import java.util.List;

public interface DocumentosCliente {
    List<ConsultarDocumentosResponse> consultarDocumentos(ConsultarDocumentoPayload consultarDocumentoPayload);

}

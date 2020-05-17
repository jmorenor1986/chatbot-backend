package co.com.santander.accesorecursos.soap.service;

import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentosPayloadResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnviarMailDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnvioDocumentoMailResponsePayload;

import java.util.List;

public interface DocumentosService {
    List<ConsultarDocumentosPayloadResponse> consultarDocumentos(ConsultarDocumentoPayload consultarDocumentoPayload);

    EnvioDocumentoMailResponsePayload enviarMailDocumentoId(EnviarMailDocumentoPayload envioDocumentoMailResponsePayload);


}

package co.com.santander.accesorecursos.soap.service.impl;

import co.com.santander.accesorecursos.soap.clients.DocumentosCliente;
import co.com.santander.accesorecursos.soap.service.DocumentosService;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentosPayloadResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnviarMailDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnvioDocumentoMailResponsePayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentosServiceImpl implements DocumentosService {

    private final DocumentosCliente documentosCliente;
    private final ModelMapper getMapper;

    @Autowired
    public DocumentosServiceImpl(DocumentosCliente documentosCliente, ModelMapper getMapper) {
        this.documentosCliente = documentosCliente;
        this.getMapper = getMapper;
    }

    @Override
    public List<ConsultarDocumentosPayloadResponse> consultarDocumentos(ConsultarDocumentoPayload consultarDocumentoPayload) {
        return documentosCliente.consultarDocumentos(consultarDocumentoPayload);
    }

    @Override
    public EnvioDocumentoMailResponsePayload enviarMailDocumentoId(EnviarMailDocumentoPayload enviarMailDocumentoPayload) {
        return EnvioDocumentoMailResponsePayload.builder()
                .respuesta(documentosCliente.enviarMailDocumentoId(enviarMailDocumentoPayload))
                .build();
    }

}

package co.com.santander.accesorecursos.soap.clients.impl;

import co.com.santander.accesorecursos.soap.clients.DocumentosClient;
import co.com.santander.accesorecursos.soap.resources.documentos.ConsultarDocumentosResponse;
import co.com.santander.accesorecursos.soap.resources.documentos.WsFelec;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentosClientImpl implements DocumentosClient {
    private final ModelMapper getMapper;
    private final WsFelec getServiceDocumentos;

    @Autowired
    public DocumentosClientImpl(ModelMapper getMapper, WsFelec getServiceDocumentos) {
        this.getMapper = getMapper;
        this.getServiceDocumentos = getServiceDocumentos;
    }

    @Override
    public List<ConsultarDocumentosResponse> consultarDocumentos(ConsultarDocumentoPayload consultarDocumentoPayload) {
        //TODO consulta
        return null;
    }
}

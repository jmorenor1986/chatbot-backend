package co.com.santander.accesorecursos.soap.clients.impl;

import co.com.santander.accesorecursos.soap.resources.documentos.WsFelec;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentosClientImpl {
    private final ModelMapper getMapper;
    private final WsFelec getServiceDocumentos;

    @Autowired
    public DocumentosClientImpl(ModelMapper getMapper, WsFelec getServiceDocumentos) {
        this.getMapper = getMapper;
        this.getServiceDocumentos = getServiceDocumentos;
    }
}

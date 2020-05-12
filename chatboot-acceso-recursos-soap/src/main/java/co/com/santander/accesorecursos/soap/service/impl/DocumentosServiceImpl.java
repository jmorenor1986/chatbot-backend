package co.com.santander.accesorecursos.soap.service.impl;

import co.com.santander.accesorecursos.soap.clients.DocumentosClient;
import co.com.santander.accesorecursos.soap.config.properties.ServiceProperties;
import co.com.santander.accesorecursos.soap.service.DocumentosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;

public class DocumentosServiceImpl implements DocumentosService {

    private final ServiceProperties serviceProperties;
    private final DocumentosClient documentosClient;
    private final ModelMapper getMapper;
    private final TokenService tokenService;

    @Autowired
    public DocumentosServiceImpl(ServiceProperties serviceProperties, DocumentosClient documentosClient, ModelMapper getMapper, TokenService tokenService) {
        this.serviceProperties = serviceProperties;
        this.documentosClient = documentosClient;
        this.getMapper = getMapper;
        this.tokenService = tokenService;
    }
}

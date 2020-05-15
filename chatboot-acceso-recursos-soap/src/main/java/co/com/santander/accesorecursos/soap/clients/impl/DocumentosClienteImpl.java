package co.com.santander.accesorecursos.soap.clients.impl;

import co.com.santander.accesorecursos.soap.clients.DocumentosCliente;
import co.com.santander.accesorecursos.soap.clients.TokenCliente;
import co.com.santander.accesorecursos.soap.common.exception.BusinessException;
import co.com.santander.accesorecursos.soap.config.properties.ServiceProperties;
import co.com.santander.accesorecursos.soap.resources.documentos.*;
import co.com.santander.accesorecursos.soap.service.SecurityService;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.TokenPayload;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class DocumentosClienteImpl implements DocumentosCliente {
    private final ModelMapper getMapper;
    private final TokenCliente tokenCliente;
    private final ServiceProperties serviceProperties;
    private final SecurityService securityService;
    private WsFelec portConsultaDocumentos;

    @Autowired
    public DocumentosClienteImpl(ModelMapper getMapper, TokenCliente tokenCliente, ServiceProperties serviceProperties, SecurityService securityService) {
        this.getMapper = getMapper;
        this.tokenCliente = tokenCliente;
        this.serviceProperties = serviceProperties;
        this.securityService = securityService;
        this.portConsultaDocumentos = (WsFelec) securityService.getService(serviceProperties.getDocumentos(), WsFelec.class);
    }

    @Override
    public List<ConsultarDocumentosResponse> consultarDocumentos(ConsultarDocumentoPayload consultarDocumentoPayload) {
        try {
            Type listType = new TypeToken<List<ConsultarDocumentosResponse>>() {
            }.getType();
            List<ResultadoConsultaDTO> resultConsultarDocs = portConsultaDocumentos.consultarDocumentos(getMapper.map(consultarDocumentoPayload, ConsultaDocDTO.class)
                    , getMapper.map(serviceProperties, BeanDatosCliente.class));
            return getMapper.map(resultConsultarDocs, listType);
        } catch (WSBusinessRuleException | WSSystemException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private String getToken() {
        return tokenCliente.generarToken(TokenPayload.builder()
                .user(serviceProperties.getUser())
                .password(serviceProperties.getPassword())
                .build());
    }

}

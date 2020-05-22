package co.com.santander.accesorecursos.soap.clients.impl;

import co.com.santander.accesorecursos.soap.clients.DocumentosCliente;
import co.com.santander.accesorecursos.soap.clients.TokenCliente;
import co.com.santander.accesorecursos.soap.common.exception.BusinessException;
import co.com.santander.accesorecursos.soap.config.properties.ServiceProperties;
import co.com.santander.accesorecursos.soap.resources.documentos.*;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentosPayloadResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnviarMailDocumentoPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.util.*;

@Service
public class DocumentosClienteImpl implements DocumentosCliente {
    private final ModelMapper getMapper;
    private final TokenCliente tokenCliente;
    private final ServiceProperties serviceProperties;
    private WsFelec portConsultaDocumentos;
    private WsFelecService getServiceDocumentos;

    @Autowired
    public DocumentosClienteImpl(ModelMapper getMapper, TokenCliente tokenCliente, ServiceProperties serviceProperties, WsFelecService getServiceDocumentos) {
        this.getMapper = getMapper;
        this.tokenCliente = tokenCliente;
        this.serviceProperties = serviceProperties;
        this.getServiceDocumentos = getServiceDocumentos;
    }


    @Override
    public List<ConsultarDocumentosPayloadResponse> consultarDocumentos(ConsultarDocumentoPayload consultarDocumentoPayload) {
        List<ResultadoConsultaDTO> resultConsultarDocs = new ArrayList<>();
        try {
            portConsultaDocumentos = getServiceDocumentos.getWsFelecPort();
            Map<String, Object> req_ctx = ((BindingProvider) portConsultaDocumentos).getRequestContext();
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            headers.put("token", Collections.singletonList(tokenCliente.generarToken()));
            req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            //Valido si el producto es NULO
            if(Objects.isNull(consultarDocumentoPayload.getProducto())){
                return new ArrayList<>();
            }
            resultConsultarDocs = portConsultaDocumentos.consultarDocumentos(getMapper.map(consultarDocumentoPayload, ConsultaDocDTO.class)
                    , setDatosUsuarioBean(consultarDocumentoPayload.getProducto().name(), consultarDocumentoPayload.getValorllave()));
            return setListResponseConsultaDocumentos(resultConsultarDocs);
        } catch (WSBusinessRuleException | WSSystemException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public String enviarMailDocumentoId(EnviarMailDocumentoPayload enviarMailDocumentoPayload) {
        try {
            portConsultaDocumentos = getServiceDocumentos.getWsFelecPort();
            Map<String, Object> req_ctx = ((BindingProvider) portConsultaDocumentos).getRequestContext();
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            headers.put("token", Collections.singletonList(tokenCliente.generarToken()));
            req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            return portConsultaDocumentos.enviarMailDocumentoId(getMapper.map(enviarMailDocumentoPayload.getConsultarDocumentoPayload(), ConsultaDocDTO.class),
                    setDatosUsuarioBean(enviarMailDocumentoPayload.getConsultarDocumentoPayload().getProducto().name(), enviarMailDocumentoPayload.getConsultarDocumentoPayload().getValorllave()),
                    getMapper.map(enviarMailDocumentoPayload.getEnvioDocumentoPayload(), DatoEnvioDTO.class));
        } catch (WSBusinessRuleException | WSSystemException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    private List<ConsultarDocumentosPayloadResponse> setListResponseConsultaDocumentos(List<ResultadoConsultaDTO> resultConsultarDocs) {
        List<ConsultarDocumentosPayloadResponse> response = new ArrayList<>();
        for (ResultadoConsultaDTO item : resultConsultarDocs) {
            response.add(getMapper.map(item, ConsultarDocumentosPayloadResponse.class));
        }
        return response;
    }

    private BeanDatosCliente setDatosUsuarioBean(String producto, String llave) {
        BeanDatosCliente result = new BeanDatosCliente();
        result.setProducto(producto);
        result.setCliente(this.serviceProperties.getCliente());
        result.setUsuarioRemoto(this.serviceProperties.getUsuarioRemoto().concat(llave));
        return result;

    }

}

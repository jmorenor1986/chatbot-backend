package co.com.santander.accesorecursos.soap.clients.impl;

import co.com.santander.accesorecursos.soap.clients.DocumentosCliente;
import co.com.santander.accesorecursos.soap.clients.TokenCliente;
import co.com.santander.accesorecursos.soap.config.properties.ServiceProperties;
import co.com.santander.accesorecursos.soap.resources.documentos.*;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DocumentosClienteImplTest {


    private ModelMapper getMapper;
    @Spy
    private WsFelecService getServiceDocumentos;
    @Mock
    private TokenCliente tokenCliente;
    private ServiceProperties serviceProperties;
    private DocumentosCliente documentosCliente;

    @Before
    public void setUp() {
        getMapper = new ModelMapper();
        MockitoAnnotations.initMocks(this);
        this.serviceProperties = new ServiceProperties();
        documentosCliente = new DocumentosClienteImpl(getMapper, , tokenCliente, serviceProperties);
    }

    @Test(expected = WebServiceException.class)
    public void testConsultarDocumentosSuccess() throws WSSystemException, WSBusinessRuleException {
        serviceProperties.setCliente("12121");
        serviceProperties.setUsuarioRemoto("12112");
        serviceProperties.setProducto("dsfsdf");
        ConsultarDocumentoPayload consultarDocumentoPayload = ConsultarDocumentoPayload.builder()
                .valorllave("121212")
                .formatoConsulta("121212")
                .folder("121212")
                .fechaIni("12121")
                .fechaFin("121121")
                .docId("1")
                .build();
        WsFelec port = getServiceDocumentos.getWsFelecPort();
        Mockito.when(tokenCliente.generarToken(Mockito.any())).thenReturn("token");
        List<ResultadoConsultaDTO> resultado = new ArrayList<>();
        Mockito.when(port.consultarDocumentos(getMapper.map(consultarDocumentoPayload, ConsultaDocDTO.class)
                , getMapper.map(serviceProperties, BeanDatosCliente.class))).thenReturn(resultado);
        Mockito.when(getMapper.map(Mockito.eq(resultado), Mockito.any())).thenReturn(new ArrayList<>());
        List<ConsultarDocumentosResponse> result = documentosCliente.consultarDocumentos(consultarDocumentoPayload);
    }
}
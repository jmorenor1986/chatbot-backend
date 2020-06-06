package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.DocumentosClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.IdDocumentoClient;
import co.com.santander.chatbot.backend.web.service.ConsultaExtractoService;
import co.com.santander.chatbot.backend.web.service.ParametrosAppService;
import co.com.santander.chatbot.backend.web.service.impl.mock.ResponseComputecMock;
import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.enums.TipoCredito;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.documento.IdDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentosPayloadResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.response.ResponseExtractosDisponibles;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ConsultaExtractoServiceImplTest {

    private ConsultaExtractoService consultaExtractoService;
    @Mock
    private DocumentosClient documentosClient;
    @Mock
    private ParametrosAppService parametrosAppService;
    @Mock
    private ClienteClient clienteClient;
    @Mock
    private IdDocumentoClient idDocumentoClient;
    @Mock
    private ModelMapper mapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.consultaExtractoService = new ConsultaExtractoServiceImpl(documentosClient, parametrosAppService, clienteClient, idDocumentoClient, mapper);
    }
    @Test
    public void testConsultaDocumentosNOT_FOUND_CLIENT(){
        String token = "1";
        ServiciosEnum servicio = ServiciosEnum.SERVICIO_CONSULTA_EXTRACTOS;
        String telefono = "3229032614";
        EnvioExtractoPayload envioExtracto = EnvioExtractoPayload.builder()
                .telefono("3229032614")
                .numeroVerificador("3229039988")
                .numeroCreditoOfuscado("xxxxx04018")
                .numeroVerificador("lsRvIEZpA2UoKQy9vxXh3JYav2v6djZo7iw=")
                .build();

        ResponseEntity<ClienteViewPayload> responseMockito = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        Mockito.doReturn(responseMockito).when(clienteClient).getClientByTelefonoAndNumCredito(Mockito.any(), Mockito.any(),Mockito.any());

        Optional<ResponseExtractosDisponibles> response = consultaExtractoService.consultaDocumentos(token, servicio, "3229032614" ,envioExtracto);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }
    @Test
    public void testConsultaDocumentosNOT_FOUND_DOCUMENTOS(){
        String token = "1";
        ServiciosEnum servicio = ServiciosEnum.SERVICIO_CONSULTA_EXTRACTOS;
        String telefono = "3229032614";
        EnvioExtractoPayload envioExtracto = EnvioExtractoPayload.builder()
                .telefono("3229032614")
                .numeroVerificador("3229039988")
                .numeroCreditoOfuscado("xxxxx04018")
                .numeroVerificador("lsRvIEZpA2UoKQy9vxXh3JYav2v6djZo7iw=")
                .build();
        //Mockito para el servicio de buscar cliente
        ResponseEntity<ClienteViewPayload> responseClienteMockito = new ResponseEntity<>(ClienteViewPayload.builder()
                .nombreCliente("LOPEZ LOPEZ LUIS EMILIO")
                .telefono("3005632010")
                .cedula("56789066")
                .email("elisabeth.becerra@samtel.co")
                .numerCredito("12345678998764531213246")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("9991")
                .idBanco("52")
                .convenio("MARCALI INTERNACIONAL SA")
                .tipoCredito(TipoCredito.VEHICULO)
                .build(),HttpStatus.OK);
        Mockito.doReturn(responseClienteMockito).when(clienteClient).getClientByTelefonoAndNumCredito(Mockito.any(), Mockito.any(),Mockito.any());
        //Mockito para el servicio de buscar
        ResponseEntity<List<ConsultarDocumentosPayloadResponse>> responseMockitoDocumentos = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Mockito.doReturn(responseMockitoDocumentos).when(documentosClient).consultaDocumentos(Mockito.any(), Mockito.any());

        Optional<ResponseExtractosDisponibles> response = consultaExtractoService.consultaDocumentos(token, servicio, "3229032614" ,envioExtracto);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());

    }

    @Test
    public void testConsultaDocumentosFOUND_DOCUMENTOS(){
        String token = "1";
        ServiciosEnum servicio = ServiciosEnum.SERVICIO_CONSULTA_EXTRACTOS;
        String telefono = "3229032614";
        EnvioExtractoPayload envioExtracto = null;
        try {
            envioExtracto = EnvioExtractoPayload.builder()
                    .telefono("3229032614")
                    .numeroVerificador("3229039988")
                    .numeroCreditoOfuscado("830000000185")
                    .numeroVerificador(SecurityUtilities.encriptar("830000000185"))
                    .build();
        }catch (Exception e){}
        //Mockito para el servicio de buscar cliente
        ResponseEntity<ClienteViewPayload> responseClienteMockito = new ResponseEntity<>(ClienteViewPayload.builder()
                .nombreCliente("LOPEZ LOPEZ LUIS EMILIO")
                .telefono("3005632010")
                .cedula("56789066")
                .email("elisabeth.becerra@samtel.co")
                .numerCredito("12345678998764531213246")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("9991")
                .idBanco("52")
                .convenio("MARCALI INTERNACIONAL SA")
                .tipoCredito(TipoCredito.VEHICULO)
                .build(),HttpStatus.OK);
        Mockito.doReturn(responseClienteMockito).when(clienteClient).getClientByTelefonoAndNumCredito(Mockito.any(), Mockito.any(),Mockito.any());
        //Mockito para el servicio de buscar
        List<ConsultarDocumentosPayloadResponse> list = new Gson().fromJson(ResponseComputecMock.RESPONSE_CONSULTAR_DOCUMENTOS, new TypeToken<List<ConsultarDocumentosPayloadResponse>>(){}.getType());
        ResponseEntity<List<ConsultarDocumentosPayloadResponse>> responseMockitoDocumentos = new ResponseEntity<>(list,HttpStatus.OK);
        Mockito.doReturn(responseMockitoDocumentos).when(documentosClient).consultaDocumentos(Mockito.any(), Mockito.any());
        //Mockito para la persistencia de los documentos
        IdDocumentoPayload idDocumentoPayload = IdDocumentoPayload.builder()
                .idDocumentos("3425624")
                .anio("2020")
                .mes("5")
                .id(1L)
                .producto("VEHICULO")
                .build();
        ResponseEntity<IdDocumentoPayload> responseMockIdDoc = new ResponseEntity<>(idDocumentoPayload, HttpStatus.OK);
        Mockito.doReturn(responseMockIdDoc).when(idDocumentoClient).save(Mockito.any(), Mockito.any());
        //Mock para obtener los parametros
        Optional<String> responseParamMock = Optional.of("12");
        Mockito.doReturn(responseParamMock).when(parametrosAppService).getParamByKey(token, "MESES_EXTRACTO");



        Optional<ResponseExtractosDisponibles> response = consultaExtractoService.consultaDocumentos(token, servicio, "3229032614" ,envioExtracto);
        Assert.assertNotNull(response);
    }


}
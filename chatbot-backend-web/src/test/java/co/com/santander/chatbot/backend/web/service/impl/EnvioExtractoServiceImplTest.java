package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.DocumentosClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.IdDocumentoClient;
import co.com.santander.chatbot.backend.web.service.EnvioExtractoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.enums.TipoCredito;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.documento.IdDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnvioDocumentoMailResponsePayload;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.ResponseEnvioExtractoPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class EnvioExtractoServiceImplTest {

    private EnvioExtractoService envioExtractoService;
    @Mock
    private DocumentosClient documentosClient;
    @Mock
    private ClienteClient clienteClient;
    @Mock
    private IdDocumentoClient idDocumentoClient;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        envioExtractoService = new EnvioExtractoServiceImpl( documentosClient,  clienteClient,  idDocumentoClient);
    }
    @Test
    public void testEnvioExtractoNOT_FOUND_CLIENT(){
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

        Optional<ResponseEnvioExtractoPayload> response = envioExtractoService.envioExtracto(token ,ServiciosEnum.SERVICIO_ENVIO_EXTRACTO, telefono, envioExtracto);
        Assert.assertNotNull(response);
    }
    @Test
    public void testEnvioExtractoSUCCESS(){
        String token = "1";
        ServiciosEnum servicio = ServiciosEnum.SERVICIO_CONSULTA_EXTRACTOS;
        String telefono = "3229032614";
        EnvioExtractoPayload envioExtracto = EnvioExtractoPayload.builder()
                .telefono("3229032614")
                .numeroVerificador("3229039988")
                .numeroCreditoOfuscado("xxxxx04018")
                .numeroVerificador("lsRvIEZpA2UoKQy9vxXh3JYav2v6djZo7iw=")
                .idExtracto(12345678)
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

        ResponseEntity<IdDocumentoPayload> responseMockitoGetDoc = new ResponseEntity<>(IdDocumentoPayload.builder()
                .id(1L)
                .producto(TipoCredito.VEHICULO.name())
                .mes("05")
                .anio("2020")
                .idDocumentos("jkdfghafñlkghñkldñ")
                .fechaIni("01/01/2019")
                .fechaFin("23/05/2020")
                .build(),HttpStatus.OK);
        Mockito.doReturn(responseMockitoGetDoc).when(idDocumentoClient).getDocumentById(Mockito.any(), Mockito.any());
        EnvioDocumentoMailResponsePayload responseMail = EnvioDocumentoMailResponsePayload.builder()
                .respuesta("Correo electrónico enviado")
                .envioExitoso(Boolean.TRUE)
                .build();
        ResponseEntity<EnvioDocumentoMailResponsePayload> responseMockitoMail = new ResponseEntity<>(responseMail, HttpStatus.OK);

        Mockito.doReturn(responseMockitoMail).when(documentosClient).envioMailDocumento(Mockito.any(), Mockito.any());

        Optional<ResponseEnvioExtractoPayload> response = envioExtractoService.envioExtracto(token ,ServiciosEnum.SERVICIO_ENVIO_EXTRACTO, telefono, envioExtracto);
        Assert.assertNotNull(response);

    }
}
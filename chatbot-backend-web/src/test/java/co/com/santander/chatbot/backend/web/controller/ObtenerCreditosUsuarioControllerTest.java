package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.CreditosUsuarioPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ObtenerCreditosUsuarioControllerTest {

    private ObtenerCreditosUsuarioController obtenerCreditosUsuarioController;
    @Mock
    private ClienteService clienteService;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        obtenerCreditosUsuarioController = new ObtenerCreditosUsuarioController(clienteService);
    }

    @Test
    public void testObtenerSUCCESS(){
        String token = "1";
        CreditosUsuarioPayload credito = CreditosUsuarioPayload.builder()
                .telefono("3005632010")
                .tipoOperacion(Long.valueOf("0"))
                .build();

        ResponseObtenerCreditosPayload respuesta = ResponseObtenerCreditosPayload.builder().build();
        respuesta.setIdRespuesta(Long.valueOf("1"));
        respuesta.setDescripcionRespuesta("Servicio consumido de forma exitosa");
        respuesta.setResultadoConsulta(Boolean.TRUE);

        Mockito.doReturn(Optional.of(respuesta)).when(clienteService).obtenerCreditos(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<ResponseObtenerCreditosPayload> response = obtenerCreditosUsuarioController.obtener(token, credito);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testObtenerFILED(){
        String token = "1";
        CreditosUsuarioPayload credito = CreditosUsuarioPayload.builder()
                .telefono("3005632010")
                .tipoOperacion(Long.valueOf("0"))
                .build();

        Mockito.when(clienteService.obtenerCreditos(Mockito.eq(token),  Mockito.eq(ServiciosEnum.SERVICIO_VALIDA_CLIENTE),Mockito.eq(credito.getTelefono()), Mockito.any())).thenReturn(Optional.empty());

        ResponseEntity<ResponseObtenerCreditosPayload> response = obtenerCreditosUsuarioController.obtener(token, credito);
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
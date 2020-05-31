package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.service.ClienteMapperService;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ClienteMapperServiceImplTest {

    private ClienteMapperService clienteMapperService;

    @Before
    public void setUp(){
        this.clienteMapperService = new ClienteMapperServiceImpl();
    }

    @Test
    public void testFromListClientViewSUCCESS(){
        List<ClienteViewPayload> clients = new ArrayList<>();
        ClienteViewPayload item = ClienteViewPayload.builder()
                .nombreCliente("LOPEZ LOPEZ LUIS EMILIO")
                .telefono("3005632010")
                .cedula("56789066")
                .email("elisabeth.becerra@samtel.co")
                .numerCredito("6000000456")
                .banco("BANCO COMERCIAL AVVILLAS")
                .estado("Cerrado")
                .idProducto("9991")
                .idBanco("52")
                .convenio("MARCALI INTERNACIONAL SA")
                .build();
        clients.add(item);
        Optional<ResponseObtenerCreditosPayload> response = clienteMapperService.fromListClientView(clients);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
        Assert.assertEquals("xxxxx00456", response.get().getCreditos().get(0).getNumeroCreditoOfuscado());
    }

    @Test
    public void testFromListClientViewSUCCESS_LONG_CREDIT(){
        List<ClienteViewPayload> clients = new ArrayList<>();
        ClienteViewPayload item = ClienteViewPayload.builder()
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
                .build();
        clients.add(item);
        Optional<ResponseObtenerCreditosPayload> response = clienteMapperService.fromListClientView(clients);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
        Assert.assertEquals("xxxxxxxxxxxxxxxxxx13246", response.get().getCreditos().get(0).getNumeroCreditoOfuscado());
    }
}
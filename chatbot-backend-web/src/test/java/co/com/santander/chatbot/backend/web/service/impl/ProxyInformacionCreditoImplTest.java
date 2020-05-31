package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.ParametrosAppClient;
import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.backend.web.service.ParametrosAppService;
import co.com.santander.chatbot.backend.web.service.ProxyInformacionCredito;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProxyInformacionCreditoImplTest {

    private ProxyInformacionCredito proxyInformacionCredito;
    @Mock
    private GenerarCertificadosService generarCertificadosService;
    @Mock
    private ClienteClient clienteClient;
    @Mock
    private ParametrosAppService parametrosAppService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.proxyInformacionCredito = new ProxyInformacionCreditoImpl(generarCertificadosService, clienteClient, parametrosAppService);
    }

    @Test
    public void testGenerarInformacionCredito() {
        String token = "1";
        InformacionCreditoPayload informacionCreditoPayload = InformacionCreditoPayload.builder()
                .telefono("3229032614")
                .build();

        Optional<InformacionCreditoResponsePayload> responseMock = Optional.of(
                InformacionCreditoResponsePayload.builder()
                        .idRespuesta("1")
                        .build()
        );
        Mockito.doReturn(responseMock).when(generarCertificadosService).generarInformacionCredito(Mockito.any(), Mockito.any(), Mockito.any());

        Optional<InformacionCreditoResponsePayload> response = proxyInformacionCredito.generarInformacionCredito(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, informacionCreditoPayload);

        Assert.assertNotNull(response);



    }

}
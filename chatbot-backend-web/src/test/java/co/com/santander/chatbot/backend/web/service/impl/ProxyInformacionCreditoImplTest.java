package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.backend.web.service.ParametrosAppService;
import co.com.santander.chatbot.backend.web.service.ProxyInformacionCredito;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;
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
                .numeroVerificador("mMdvIEZpB2UpIGe05u/tr4jyjtMEUiFaf2FGCg==")
                .build();

        Optional<InformacionCreditoResponsePayload> responseMock = Optional.of(
                InformacionCreditoResponsePayload.builder()
                        .idRespuesta("1")
                        .build()
        );
        Mockito.doReturn(responseMock).when(generarCertificadosService).generarInformacionCredito(Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<ClienteViewPayload> responseMockCliente = new ResponseEntity<>(ClienteViewPayload.builder()
                .fechaDesembolso(new Date("01/05/2020"))
                .valorDesembolso(new BigDecimal(10000000))
                .saldoCapital(new BigDecimal(1000000))
                .build(), HttpStatus.OK);
        Mockito.doReturn(responseMockCliente).when(clienteClient).getClientByTelefonoAndNumCredito(Mockito.any(), Mockito.any(), Mockito.any());

        Optional<String> responseParam = Optional.of("200");

        Mockito.doReturn(responseParam).when(parametrosAppService).getParamByKey(Mockito.any(), Mockito.any());

        Optional<InformacionCreditoResponsePayload> response = proxyInformacionCredito.generarInformacionCredito(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, informacionCreditoPayload);

        Assert.assertNotNull(response);
    }

    @Test
    public void testGenerarInformacionCreditoFAILED() {
        String token = "1";
        InformacionCreditoPayload informacionCreditoPayload = InformacionCreditoPayload.builder()
                .telefono("3229032614")
                .numeroVerificador("mMdvIEZpB2UpIGe05u/tr4jyjtMEUiFaf2FGCg==")
                .build();

        Optional<InformacionCreditoResponsePayload> responseMock = Optional.of(
                InformacionCreditoResponsePayload.builder()
                        .idRespuesta("1")
                        .build()
        );
        Mockito.doReturn(responseMock).when(generarCertificadosService).generarInformacionCredito(Mockito.any(), Mockito.any(), Mockito.any());

        ResponseEntity<ClienteViewPayload> responseMockCliente = new ResponseEntity<>(ClienteViewPayload.builder()
                .fechaDesembolso(new Date("01/05/2020"))
                .valorDesembolso(new BigDecimal(10000000))
                .saldoCapital(new BigDecimal(5000000))
                .build(), HttpStatus.OK);
        Mockito.doReturn(responseMockCliente).when(clienteClient).getClientByTelefonoAndNumCredito(Mockito.any(), Mockito.any(), Mockito.any());

        Optional<String> responseParam = Optional.of("200");

        Mockito.doReturn(responseParam).when(parametrosAppService).getParamByKey(Mockito.any(), Mockito.any());

        Optional<InformacionCreditoResponsePayload> response = proxyInformacionCredito.generarInformacionCredito(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, informacionCreditoPayload);

        Assert.assertNotNull(response);
    }

}
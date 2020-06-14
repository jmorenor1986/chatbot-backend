package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.ClienteClient;
import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import co.com.santander.chatbot.backend.web.service.GuardarTransaccionCertificadoService;
import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.enums.TipoCredito;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.GenericCertificatePayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoPayload;
import co.com.santander.chatbot.domain.payload.service.certificados.InformacionCreditoResponsePayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.GeneralSecurityException;
import java.util.Optional;

@SpringBootTest
public class GenerarCertificadosServiceImplTest {

    private GenerarCertificadosService generarCertificadosService;
    private String token;
    private String telefono;
    private String numeroVerificador;
    @Mock
    private GuardarTransaccionCertificadoService guardarTransaccionCertificadoService;
    @Mock
    private ClienteClient clienteClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        generarCertificadosService = new GenerarCertificadosServiceImpl(guardarTransaccionCertificadoService, clienteClient);
        telefono = "12345";
        token = "12345";
        numeroVerificador = "kcZsJENvAG1YUB7AZpHm5hhs2L4ZABjKyQD0IA";
    }

    @Test
    public void testGenerarInformacionCreditoSUCCESS() throws GeneralSecurityException {
        ClienteViewPayload clienteViewPayload = ClienteViewPayload.builder()
                .banco("ff")
                .cedula("123456")
                .convenio("1234")
                .email("johnmorenoing@gmail.com")
                .estado("0")
                .idBanco("1212")
                .idProducto("1121")
                .nombreCliente("aasad")
                .numerCredito("6000000457")
                .telefono("13444")
                .tipoCredito(TipoCredito.VEHICULO)
                .build();
        InformacionCreditoPayload informacionCreditoPayload = InformacionCreditoPayload.builder()
                .telefono(telefono)
                .numeroVerificador(numeroVerificador)
                .build();
        Mockito.when(clienteClient.getClientByTelefonoAndNumCredito(token, informacionCreditoPayload.getTelefono(), SecurityUtilities.desencriptar(informacionCreditoPayload.getNumeroVerificador()))).thenReturn(new ResponseEntity<>(clienteViewPayload, HttpStatus.OK));
        Mockito.when(guardarTransaccionCertificadoService.generarCertificado(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_INFORMACION_CREDITO), Mockito.eq(CertificadoPayload.builder()
                .numeroCredito(informacionCreditoPayload.getNumeroVerificador())
                .identificacion(clienteViewPayload.getCedula())
                .build()), Mockito.any(), Mockito.eq(1L))).thenReturn(Optional.of(ResponsePayload.builder()
                .resultadoValidacion(Boolean.TRUE)
                .build()));
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarInformacionCredito(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, informacionCreditoPayload);
        Assert.assertNotNull(result);
    }

    @Test(expected = GeneralSecurityException.class)
    public void testGenerarInformacionCreditoErrorDesencriptar() throws GeneralSecurityException {
        InformacionCreditoPayload informacionCreditoPayload = InformacionCreditoPayload.builder()
                .telefono(telefono)
                .numeroVerificador(numeroVerificador)
                .build();
        Mockito.when(clienteClient.getClientByTelefonoAndNumCredito(token, informacionCreditoPayload.getTelefono(), SecurityUtilities.desencriptar("1234"))).thenReturn(new ResponseEntity<>(ClienteViewPayload.builder().build(), HttpStatus.OK));
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarInformacionCredito(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, informacionCreditoPayload);
    }

    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarInformacionCreditoClienteDatosIncorrectos() throws GeneralSecurityException {
        ClienteViewPayload clienteViewPayload = ClienteViewPayload.builder()
                .banco("ff")
                .cedula("123456")
                .convenio("1234")
                .email("johnmorenoing@gmail.com")
                .estado("0")
                .idBanco("1212")
                .idProducto("1121")
                .nombreCliente("aasad")
                .numerCredito("6000000457")
                .telefono("13444")
                .build();
        InformacionCreditoPayload informacionCreditoPayload = InformacionCreditoPayload.builder()
                .telefono(telefono)
                .numeroVerificador(numeroVerificador)
                .build();
        Mockito.when(clienteClient.getClientByTelefonoAndNumCredito(token, informacionCreditoPayload.getTelefono(), SecurityUtilities.desencriptar(informacionCreditoPayload.getNumeroVerificador()))).thenReturn(new ResponseEntity<>(clienteViewPayload, HttpStatus.OK));
        Mockito.when(guardarTransaccionCertificadoService.generarCertificado(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_INFORMACION_CREDITO), Mockito.eq(CertificadoPayload.builder()
                .numeroCredito(informacionCreditoPayload.getNumeroVerificador())
                .identificacion(clienteViewPayload.getCedula())
                .build()), Mockito.any(), Mockito.eq(1L))).thenReturn(Optional.empty());
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarInformacionCredito(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, informacionCreditoPayload);
        Assert.assertNotNull(result);
    }

    @Test
    public void testGenerarCertificadoEstandar() throws GeneralSecurityException {
        ClienteViewPayload clienteViewPayload = ClienteViewPayload.builder()
                .banco("ff")
                .cedula("123456")
                .convenio("1234")
                .email("johnmorenoing@gmail.com")
                .estado("0")
                .idBanco("1212")
                .idProducto("1121")
                .nombreCliente("aasad")
                .numerCredito("6000000457")
                .telefono("13444")
                .tipoCredito(TipoCredito.VEHICULO)
                .build();
        GenericCertificatePayload genericCertificatePayload = GenericCertificatePayload.builder()
                .numeroVerificador(numeroVerificador)
                .telefono(telefono)
                .build();
        Mockito.when(clienteClient.getClientByTelefonoAndNumCredito(token, genericCertificatePayload.getTelefono(), SecurityUtilities.desencriptar(genericCertificatePayload.getNumeroVerificador()))).thenReturn(new ResponseEntity<>(clienteViewPayload, HttpStatus.OK));
        Mockito.when(guardarTransaccionCertificadoService.generarCertificado(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_INFORMACION_CREDITO), Mockito.eq(CertificadoPayload.builder()
                .numeroCredito(genericCertificatePayload.getNumeroVerificador())
                .identificacion(clienteViewPayload.getCedula())
                .build()), Mockito.any(), Mockito.eq(1L))).thenReturn(Optional.of(ResponsePayload.builder()
                .resultadoValidacion(Boolean.TRUE)
                .build()));
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, genericCertificatePayload, 1L);
        Assert.assertNotNull(result);
    }


    @Test(expected = ValidateStateCertificateException.class)
    public void testGenerarCertificadoEstandarClienteDatosIncorrectos() throws GeneralSecurityException {
        ClienteViewPayload clienteViewPayload = ClienteViewPayload.builder()
                .banco("ff")
                .cedula("123456")
                .convenio("1234")
                .email("johnmorenoing@gmail.com")
                .estado("0")
                .idBanco("1212")
                .idProducto("1121")
                .nombreCliente("aasad")
                .numerCredito("6000000457")
                .telefono("13444")
                .build();
        GenericCertificatePayload genericCertificatePayload = GenericCertificatePayload.builder()
                .numeroVerificador(numeroVerificador)
                .telefono(telefono)
                .build();
        Mockito.when(clienteClient.getClientByTelefonoAndNumCredito(token, genericCertificatePayload.getTelefono(), SecurityUtilities.desencriptar(genericCertificatePayload.getNumeroVerificador()))).thenReturn(new ResponseEntity<>(clienteViewPayload, HttpStatus.OK));
        Mockito.when(guardarTransaccionCertificadoService.generarCertificado(Mockito.eq(token), Mockito.eq(ServiciosEnum.SERVICIO_INFORMACION_CREDITO), Mockito.eq(CertificadoPayload.builder()
                .numeroCredito(genericCertificatePayload.getNumeroVerificador())
                .identificacion(clienteViewPayload.getCedula())
                .build()), Mockito.any(), Mockito.eq(1L))).thenReturn(Optional.empty());
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, genericCertificatePayload, 1L);
        Assert.assertNotNull(result);
    }

    @Test(expected = GeneralSecurityException.class)
    public void ttestGenerarCertificadoEstandarErrorDesencriptar() throws GeneralSecurityException {
        GenericCertificatePayload genericCertificatePayload = GenericCertificatePayload.builder()
                .numeroVerificador(numeroVerificador)
                .telefono(telefono)
                .build();
        Mockito.when(clienteClient.getClientByTelefonoAndNumCredito(token, genericCertificatePayload.getTelefono(), SecurityUtilities.desencriptar("1234"))).thenReturn(new ResponseEntity<>(ClienteViewPayload.builder().build(), HttpStatus.OK));
        Optional<InformacionCreditoResponsePayload> result = generarCertificadosService.generarCertificadoEstandar(token, ServiciosEnum.SERVICIO_INFORMACION_CREDITO, genericCertificatePayload, 1L);
    }

}
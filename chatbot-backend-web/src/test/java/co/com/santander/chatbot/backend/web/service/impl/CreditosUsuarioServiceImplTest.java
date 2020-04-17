package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.acceso.recursos.clients.core.CreditosUsuarioClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.dto.RequestDto;
import co.com.santander.chatbot.acceso.recursos.clients.core.dto.ResponseDto;
import co.com.santander.chatbot.backend.web.service.CreditosUsuarioService;
import co.com.santander.chatbot.domain.dto.accesorecursos.RespuestaDto;
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
public class CreditosUsuarioServiceImplTest {
    @Mock
    private CreditosUsuarioClient creditosUsuarioClient;

    private CreditosUsuarioService creditosUsuarioService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        creditosUsuarioService = new CreditosUsuarioServiceImpl(creditosUsuarioClient);
    }

    @Test
    public void testConsultarCreditosUsuario() {
        Mockito.when(creditosUsuarioClient.conusltarCreditosCliente(RequestDto.builder()
                .tipoOperacion(2)
                .telefono("1234")
                .build())).thenReturn(new ResponseEntity<>(ResponseDto.builder().build(), HttpStatus.OK));
        Optional<RespuestaDto> result = creditosUsuarioService.consultarCreditosUsuario("1234", 2);
        Assert.assertNotNull(result);

    }

}
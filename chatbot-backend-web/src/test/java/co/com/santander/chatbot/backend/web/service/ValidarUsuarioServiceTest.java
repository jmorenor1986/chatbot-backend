package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.acceso.recursos.clients.core.CreditosUsuarioClient;
import co.com.santander.chatbot.acceso.recursos.clients.core.dto.RequestDto;
import co.com.santander.chatbot.acceso.recursos.clients.core.dto.ResponseDto;
import co.com.santander.chatbot.backend.web.service.impl.CreditosUsuarioServiceImpl;
import co.com.santander.chatbot.domain.dto.accesorecursos.RespuestaDto;
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
public class ValidarUsuarioServiceTest {

    @Mock
    private CreditosUsuarioClient creditosUsuarioClient;

    private CreditosUsuarioService creditosUsuarioService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        creditosUsuarioService = new CreditosUsuarioServiceImpl(creditosUsuarioClient);
    }
    @Test
    public void testConsultarCreditosSuccess() {
        String telefono = "3005632010";
        int tipoOperacion = 1;
        Mockito.when(creditosUsuarioClient.conusltarCreditosCliente(RequestDto.builder().build())).thenReturn(new ResponseEntity<>(ResponseDto.builder().build(), HttpStatus.OK));
        Optional<RespuestaDto> result = creditosUsuarioService.consultarCreditosUsuario(telefono,tipoOperacion);
    }

}

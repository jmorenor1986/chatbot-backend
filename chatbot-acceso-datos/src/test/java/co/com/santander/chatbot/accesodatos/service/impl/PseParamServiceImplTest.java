package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.PseParam;
import co.com.santander.chatbot.accesodatos.repository.PseParamRepository;
import co.com.santander.chatbot.accesodatos.service.PseParamService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PseParamServiceImplTest {

    private PseParamService pseParamService;

    @Mock
    private PseParamRepository pseParamRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.pseParamService = new PseParamServiceImpl(pseParamRepository);
    }

    @Test
    public void testFindByIdBancoAndTipoCredito() {
        Long idBanco = 9000L;
        Long tipoCredito = 1L;

        Optional<PseParam> responseMockito = Optional.of(PseParam.builder()
                .id(1L)
                .idBanco(9000L)
                .tipoCredito(1L)
                .url("https://url1")
                .build());
        Mockito.doReturn(responseMockito).when(pseParamRepository).findByIdBancoAndTipoCredito(Mockito.any(), Mockito.any());

        Optional<PseParam> response = pseParamService.findByIdBancoAndTipoCredito(idBanco, tipoCredito);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }
}
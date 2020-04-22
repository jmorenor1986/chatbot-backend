package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.common.aspect.validate.DatosTest;
import co.com.santander.chatbot.backend.web.service.GenerarCertificadosService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class GenerarCertificadosServiceImplTest {

    private GenerarCertificadosService generarCertificadosService;

    @Before
    public void init() {
        generarCertificadosService = new GenerarCertificadosServiceImpl();
    }

    @Test
    public void testGenerarCertificado() {
        DatosTest datosTest = DatosTest.builder().build();
        Optional<String> result = generarCertificadosService.generarCertificado("", datosTest);
        Assert.assertNotNull(result);
    }
}

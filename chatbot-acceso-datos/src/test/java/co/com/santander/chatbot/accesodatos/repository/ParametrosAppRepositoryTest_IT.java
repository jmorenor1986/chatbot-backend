package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.ParametrosApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ParametrosAppRepositoryTest_IT {

    @Autowired
    private ParametrosAppRepository parametrosAppRepository;
    @Before
    public void setUp(){
        ParametrosApp parametrosApp = ParametrosApp.builder()
                .clave("DIAS_DESEMBOLSO")
                .valor("70")
                .build();
        parametrosAppRepository.save(parametrosApp);
    }

    @Test
    public void testFindByValor(){
        Optional<ParametrosApp> result = parametrosAppRepository.findByClave("DIAS_DESEMBOLSO");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isPresent());
    }

}
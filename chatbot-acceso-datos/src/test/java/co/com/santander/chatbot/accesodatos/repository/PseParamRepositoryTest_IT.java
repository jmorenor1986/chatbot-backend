package co.com.santander.chatbot.accesodatos.repository;


import co.com.santander.chatbot.accesodatos.entity.PseParam;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class PseParamRepositoryTest_IT {

    @Autowired
    private PseParamRepository pseParamRepository;

    @Before
    public void setUp(){

    }

    @Test
    public void testFindByIdBancoAndTipoCredito(){
        Long idBanco = 9000L;
        Long tipoCredito  = 1L;
        Optional<PseParam> response =  pseParamRepository.findByIdBancoAndTipoCredito(idBanco, tipoCredito);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isPresent());
    }

}
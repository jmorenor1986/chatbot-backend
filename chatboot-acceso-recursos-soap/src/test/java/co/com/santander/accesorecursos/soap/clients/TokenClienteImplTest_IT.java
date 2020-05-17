package co.com.santander.accesorecursos.soap.clients;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenClienteImplTest_IT {

    @Autowired
    private TokenCliente tokenCliente;

    @Test
    public void testGenerateTokenSuccess() {
        String result = tokenCliente.generarToken();
        System.out.println(result);
        Assert.assertNotNull(result);
    }

}

package co.com.santander.accesorecursos.soap.clients;

import co.com.santander.accesorecursos.soap.common.BusinessException;
import co.com.santander.chatbot.domain.payload.enviarextracto.TokenPayload;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenClienteImplTest_IT {

    @Autowired
    private TokenCliente tokenCliente;



    @Value("${services.user}")
    private String usuario;
    @Value("${services.password}")
    private String password;

    @Ignore
    @Test
    public void testGenerateTokenSuccess() {
        TokenPayload user = TokenPayload.builder()
                .password(password)
                .user(usuario)
                .build();
        String result = tokenCliente.generarToken(user);
        Assert.assertNotNull(result);
    }

    @Test(expected = BusinessException.class)
    public void testGenerateTokenError() {
        TokenPayload user = TokenPayload.builder()
                .password("12345678")
                .user("john")
                .build();
        String result = tokenCliente.generarToken(user);
    }
}

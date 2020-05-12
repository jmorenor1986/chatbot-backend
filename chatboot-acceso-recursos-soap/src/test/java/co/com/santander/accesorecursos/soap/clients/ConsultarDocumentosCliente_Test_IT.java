package co.com.santander.accesorecursos.soap.clients;

import co.com.santander.accesorecursos.soap.common.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConsultarDocumentosCliente_Test_IT {
    @Autowired
    private DocumentosCliente documentosCliente;

    @Test(expected = BusinessException.class)
    public void testConsultarDocumentosError() {

    }


}

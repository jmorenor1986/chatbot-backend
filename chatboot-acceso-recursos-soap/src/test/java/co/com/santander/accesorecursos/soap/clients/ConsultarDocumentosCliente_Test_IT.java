package co.com.santander.accesorecursos.soap.clients;

import co.com.santander.accesorecursos.soap.common.exception.BusinessException;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentosPayloadResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConsultarDocumentosCliente_Test_IT {
    @Autowired
    private DocumentosCliente documentosCliente;


    @Test
    public void testConsultarDocumentos() {
        List<ConsultarDocumentosPayloadResponse> result = documentosCliente.consultarDocumentos(ConsultarDocumentoPayload.builder()
                .docId("")
                .fechaFin("01/01/2020")
                .fechaIni("01/01/2000")
                .folder("")
                .formatoConsulta("pdf")
                .valorllave("41584206")
                .build());
        Assert.assertNotNull(result);
    }


    @Test(expected = BusinessException.class)
    public void testConsultarDocumentosError() {
        List<ConsultarDocumentosPayloadResponse> result = documentosCliente.consultarDocumentos(ConsultarDocumentoPayload.builder()
                .docId("")
                .fechaFin("01/01/2020")
                .fechaIni("01/01/2000")
                .folder("")
                .formatoConsulta("pdf")
                .valorllave("")
                .build());
    }
}

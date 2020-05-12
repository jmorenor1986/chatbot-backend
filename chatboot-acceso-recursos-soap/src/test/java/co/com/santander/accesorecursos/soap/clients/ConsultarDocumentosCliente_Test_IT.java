package co.com.santander.accesorecursos.soap.clients;

import co.com.santander.accesorecursos.soap.common.exception.BusinessException;
import co.com.santander.accesorecursos.soap.resources.documentos.ConsultarDocumentosResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
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


    @Test(expected = BusinessException.class)
    public void testConsultarDocumentosError() {
        List<ConsultarDocumentosResponse> result = documentosCliente.consultarDocumentos(ConsultarDocumentoPayload.builder()
                .docId("1")
                .fechaFin("")
                .fechaIni("1212")
                .folder("1")
                .formatoConsulta("1")
                .valorllave("1")
                .build());
    }


}

package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.ConsultarDocumentosPayloadResponse;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnviarMailDocumentoPayload;
import co.com.santander.chatbot.domain.payload.enviarextracto.EnvioDocumentoMailResponsePayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "documentos", url = "${clientes.url.baseUrlSoa}")
public interface DocumentosClient {

    @PostMapping(value = "${clientes.url.consultaDocumentos}")
    ResponseEntity<List<ConsultarDocumentosPayloadResponse>> consultaDocumentos(@RequestHeader("Authorization") String bearerToken, @RequestBody ConsultarDocumentoPayload consultarDocumentoPayload);

    @PostMapping(value = "${clientes.url.envioMailDocumentos}")
    ResponseEntity<EnvioDocumentoMailResponsePayload> envioMailDocumento(@RequestHeader("Authorization") String bearerToken, @RequestBody EnviarMailDocumentoPayload enviarMailDocumentoPayload);
}

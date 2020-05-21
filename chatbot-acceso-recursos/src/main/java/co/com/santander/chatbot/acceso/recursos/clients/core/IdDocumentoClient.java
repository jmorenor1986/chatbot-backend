package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.documento.IdDocumentoPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "idDocumentos", url = "${clientes.url.baseUrlCliente}")
public interface IdDocumentoClient {

    @PostMapping(value = "${clientes.url.insercionIdDocumento}")
    ResponseEntity<IdDocumentoPayload> save(@RequestHeader("Authorization") String bearerToken, @RequestBody IdDocumentoPayload idDocumentoPayload);

    @GetMapping(value = "/{id}/")
    ResponseEntity<IdDocumentoPayload> getDocumentById(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") Long id);

}
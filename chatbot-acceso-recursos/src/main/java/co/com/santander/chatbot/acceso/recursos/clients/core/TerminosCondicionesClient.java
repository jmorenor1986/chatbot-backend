package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.TerminosCondicionesPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "terminosCondicionesService", url = "${clientes.url.baseUrlCliente}")
public interface TerminosCondicionesClient {

    @PostMapping(value = "${clientes.url.terminosCondiciones}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TerminosCondicionesPayload> save(@RequestHeader("Authorization") String bearerToken, @RequestBody TerminosCondicionesPayload terminosCondiciones);

}

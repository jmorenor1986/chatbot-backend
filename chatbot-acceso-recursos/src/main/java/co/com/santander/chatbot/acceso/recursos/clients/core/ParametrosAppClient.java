package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosAppPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "parametosAppCliente", url = "${clientes.url.baseUrlCliente}")
public interface ParametrosAppClient {

    String AUTH_TOKEN = "Authorization";

    @GetMapping(value = "${clientes.url.consultaParametosApp}")
    ResponseEntity<ParametrosAppPayload> getByClave(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam(value = "clave") String clave);

    @PostMapping("${clientes.url.saveParametosApp}")
    ResponseEntity<ParametrosAppPayload> save(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody ParametrosAppPayload parametros );
}

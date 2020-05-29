package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosAppPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "parametosAppCliente", url = "${clientes.url.baseUrlCliente}")
public interface ParametrosAppClient {

    @GetMapping(value = "${clientes.url.consultaParametosApp}")
    ResponseEntity<ParametrosAppPayload> getByClave(@RequestParam(value = "clave") String clave);

    @PostMapping("${clientes.url.saveParametosApp}")
    ResponseEntity<ParametrosAppPayload> save( @RequestBody ParametrosAppPayload parametros );
}

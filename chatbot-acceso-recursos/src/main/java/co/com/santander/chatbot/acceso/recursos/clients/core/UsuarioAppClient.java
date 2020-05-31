package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.UsuarioAppPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "usuario-app", url = "${clientes.url.baseUrlCliente}")
public interface UsuarioAppClient {

    @PostMapping(value = "/v1/usuarioapp/login/")
    ResponseEntity<Boolean> validateUser(@RequestBody UsuarioAppPayload usuarioPayload);
}

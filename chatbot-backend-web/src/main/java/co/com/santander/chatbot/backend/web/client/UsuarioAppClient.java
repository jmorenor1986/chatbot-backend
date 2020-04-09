package co.com.santander.chatbot.backend.web.client;

import co.com.santander.chatbot.domain.payload.accesodatos.UsuarioAppPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${feignclient.usuario-app.service-name}", url = "${feignclient.usuario-app.url}")
public interface UsuarioAppClient {

    @PostMapping(value = "/login/")
    ResponseEntity<Boolean> validateUser(@RequestBody UsuarioAppPayload usuarioPayload);
}

package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "cliente", url = "${clientes.url.baseUrlCliente}")
public interface ClienteClient {

    @PostMapping(value = "${clientes.url.consultaCliente}")
    public ResponseEntity<ResponsePayload> conusltarCliente(@RequestHeader("Authorization") String bearerToken, ClientePayload cliente);

}

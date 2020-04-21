package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "cliente", url = "${clientes.url.baseUrlCliente}")
public interface ClienteClient {

    String AUTH_TOKEN = "Authorization";

    @PostMapping(value = "${clientes.url.consultaCliente}")
    public ResponseEntity<ResponsePayload> conusltarCliente(@RequestHeader(AUTH_TOKEN) String bearerToken, ClientePayload cliente);

    @GetMapping(value = "${clientes.url.consultaCliente}?telefono=3005632010", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClienteViewPayload>> getClientsByTel(@RequestHeader(AUTH_TOKEN) String bearerToken, @Param(value = "telefono") String telefono);
}

package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.cliente.ClienteViewPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cliente", url = "${clientes.url.baseUrlCliente}")
public interface ClienteClient {

    String AUTH_TOKEN = "Authorization";

    @PostMapping(value = "${clientes.url.consultaCliente}")
    ResponseEntity<ResponsePayload> conusltarCliente(@RequestHeader(AUTH_TOKEN) String bearerToken, ClientePayload cliente);

    @GetMapping(value = "${clientes.url.consultaCliente}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClienteViewPayload>> getClientsByTel(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam(value = "telefono") String telefono);

    @GetMapping(value = "${clientes.url.consultaClienteByTelAndNumCred}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClienteViewPayload> getClientByTelefonoAndNumCredito(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam(value = "telefono") String telefono, @RequestParam(value = "numCredito") String numCredito);

    @GetMapping(value = "${clientes.url.consultaCedulaAndCredito}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClienteViewPayload> getClientByCedulaAndNumCredito(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestParam(value = "cedula") String cedula, @RequestParam(value = "numCredito") String numCredito);
}

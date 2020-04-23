package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "parametrosService", url = "${clientes.url.baseUrlCliente}")
public interface ParametrosServiceClient {

    @PostMapping(value = "${clientes.url.consultaProcesoParametros}")
    public ResponseEntity<ResponsePayload> consultarProcesoParametros(@RequestHeader("Authorization") String bearerToken, @RequestBody ValidarProcesoPayload validarProcesoPayload);
}

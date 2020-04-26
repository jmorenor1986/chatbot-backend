package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.common.utilities.GenericLogPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "logCliente", url = "${clientes.url.baseUrlCliente}")
public interface LogCliente {

    @PostMapping(value = "${clientes.url.logger}")
    ResponseEntity<Boolean> insertarLog(@RequestHeader("Authorization") String bearerToken, @RequestBody GenericLogPayload genericLogPayload);
}

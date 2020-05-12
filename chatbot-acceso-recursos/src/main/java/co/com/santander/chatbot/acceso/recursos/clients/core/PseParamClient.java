package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.PseParamPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pseParamService", url = "${clientes.url.baseUrlCliente}")
public interface PseParamClient {

    @GetMapping(value = "${clientes.url.pseUrl}")
    ResponseEntity<PseParamPayload> getByIdBancoAndTipoCredito(@RequestHeader("Authorization") String bearerToken, @RequestParam(value = "idBanco") Long idBanco, @RequestParam(value = "tipoCredito") Long tipoCredito );
}
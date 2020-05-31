package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.acceso.recursos.clients.core.dto.RequestDto;
import co.com.santander.chatbot.acceso.recursos.clients.core.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "creditoUsuario", url = "${clientes.url.baseUrlCliente}")
public interface CreditosUsuarioClient {

    @PostMapping(value = "${clientes.url.creditosUsuario}")
    ResponseEntity<ResponseDto> conusltarCreditosCliente(RequestDto requestDto);

}

package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.acceso.recursos.clients.core.dto.RequestDto;
import co.com.santander.chatbot.acceso.recursos.clients.core.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "creditoUsuario", url = "${clientes.url.base}")
public interface CreditosUsuarioClient {
    @RequestMapping(value = "${clientes.url.creditosUsuario}", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> conusltarCreditosCliente(RequestDto requestDto);

}

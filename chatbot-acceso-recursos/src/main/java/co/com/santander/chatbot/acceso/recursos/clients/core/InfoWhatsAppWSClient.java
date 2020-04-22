package co.com.santander.chatbot.acceso.recursos.clients.core;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "infoWhatsapp", url = "${clientes.url.baseUrlCliente}")
public interface InfoWhatsAppWSClient {

    @GetMapping(value = "${clientes.url.consultaProcesoExistente}")
    public ResponseEntity<Boolean> validateExistingProcess(@RequestHeader("Authorization") String bearerToken,
                                                           @RequestParam(value = "numCreditoBanco") String numCreditoBanco,
                                                           @RequestParam(value = "numeroIdentificacion") String numeroIdentificacion,
                                                           @RequestParam(value = "numPeticionServicio") Long numPeticionServicio);
}

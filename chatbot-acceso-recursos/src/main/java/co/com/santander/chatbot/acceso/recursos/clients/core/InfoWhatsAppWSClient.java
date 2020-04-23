package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "infoWhatsapp", url = "${clientes.url.baseUrlCliente}")
public interface InfoWhatsAppWSClient {

    @GetMapping(value = "${clientes.url.consultaProcesoExistente}")
    public ResponseEntity<InfoWhatsAppWSPayload> validateExistingProcess(@RequestHeader("Authorization") String bearerToken,
                                                                         @RequestParam(value = "numCreditoBanco") String numCreditoBanco,
                                                                         @RequestParam(value = "numeroIdentificacion") String numeroIdentificacion,
                                                                         @RequestParam(value = "numPeticionServicio") Long numPeticionServicio);

    @PostMapping(value = "${clientes.url.guardarProceso}")
    public ResponseEntity<InfoWhatsAppWSPayload> save(@RequestHeader("Authorization") String bearerToken, @RequestBody InfoWhatsAppWSPayload infoWhatsAppWSPayload);
}

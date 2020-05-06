package co.com.santander.chatbot.acceso.recursos.clients.core;

import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosServicioPayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "parametrosService", url = "${clientes.url.baseUrlCliente}")
public interface ParametrosServiceClient {

    @PostMapping(value = "${clientes.url.consultaProcesoParametros}")
    ResponseEntity<ResponsePayload> consultarProcesoParametros(@RequestHeader("Authorization") String bearerToken, @RequestBody ValidarProcesoPayload validarProcesoPayload);

    @GetMapping(value = "${clientes.url.consultaParametrosByServicio}")
    ResponseEntity<ParametrosServicioPayload> getParametroService(@RequestHeader("Authorization") String bearerToken, @RequestParam(value = "servicio") String servicioEnum);

}

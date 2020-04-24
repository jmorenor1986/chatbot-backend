package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/validarCliente")
public class ValidarClienteController {

    private final ClienteService clienteService;

    public ValidarClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<ResponsePayload> validar(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody ClientePayload clientePayload) {
        ResponseEntity<ResponsePayload> response = clienteService.validarCliente(bearerToken, ServiciosEnum.SERVICIO_VALIDA_CLIENTE, clientePayload.getTelefono(), clientePayload);
        return response;
    }
}

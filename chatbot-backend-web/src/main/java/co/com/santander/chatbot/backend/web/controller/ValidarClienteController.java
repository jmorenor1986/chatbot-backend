package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/validarCliente")
public class ValidarClienteController {

    private final String AUTH_TOKEN = "Authorization";
    private final ClienteService clienteService;

    public ValidarClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ResponsePayload> validar(@RequestHeader(AUTH_TOKEN) String bearerToken, @Valid @RequestBody ClientePayload clientePayload) {
        return clienteService.validarCliente(clientePayload, bearerToken);
    }
}

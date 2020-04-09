package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.UsuarioInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/usuario")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> consultaCliente(@Valid @RequestBody UsuarioInput input) {
        Boolean result = clienteService.consultarCliente(input.getTelefono(), input.getColaIdentificacion()).get();
        return new ResponseEntity<>(result, result == Boolean.TRUE ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.service.ClienteService;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("v1/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponsePayload> consultaCliente(@Valid @RequestBody ClientePayload input) {
        Optional<Boolean> result = clienteService.consultarCliente(input.getTelefono(), input.getCedula());
        if (result.isPresent())
            return new ResponseEntity<>(ResponsePayload.builder()
                    .resultadoValidacion(result.get())
                    .idRespuesta(0)
                    .descripcionRespuesta(result.get().toString())
                    .build(), result.get().equals(Boolean.TRUE) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(ResponsePayload.builder()
                .resultadoValidacion(Boolean.TRUE)
                .idRespuesta(0)
                .descripcionRespuesta("Error consultando el servicio")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
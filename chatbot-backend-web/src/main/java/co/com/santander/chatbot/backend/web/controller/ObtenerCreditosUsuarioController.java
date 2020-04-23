package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.CreditosUsuarioPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/obtenerCreditosUsuario")
public class ObtenerCreditosUsuarioController {

    private final ClienteService clienteService;

    @Autowired
    public ObtenerCreditosUsuarioController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObtenerCreditosPayload> obtener(@RequestHeader("Authorization") String bearerToken, @RequestBody CreditosUsuarioPayload credito) {
        Optional<ResponseObtenerCreditosPayload> response = clienteService.obtenerCreditos(bearerToken, ServiciosEnum.SERVICIO_OBTENER_CREDITOS, credito.getTelefono());
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
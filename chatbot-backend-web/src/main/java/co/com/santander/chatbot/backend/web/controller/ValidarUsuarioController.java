package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.controller.payload.RespuestaPayload;
import co.com.santander.chatbot.backend.web.controller.payload.UsuarioPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/validarUsuario")
public class ValidarUsuarioController {

    @GetMapping("/")
    public ResponseEntity<RespuestaPayload> validar(@RequestBody UsuarioPayload usuarioPayload) {
        return new ResponseEntity<>(RespuestaPayload.builder()
                .resultadoValidacion(true)
                .idRespuesta(0L)
                .descripcionRespuesta("Servicio consumido de forma exitosa")
                .build(), HttpStatus.OK);
    }
}

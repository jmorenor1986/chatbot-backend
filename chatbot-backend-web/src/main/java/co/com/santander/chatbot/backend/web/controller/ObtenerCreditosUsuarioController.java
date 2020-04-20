package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.domain.payload.service.obtenercreditos.CreditosUsuarioPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/obtenerCreditosUsuario")
public class ObtenerCreditosUsuarioController {

    private final String AUTH_TOKEN = "Authorization";

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObtenerCreditosPayload> obtener(@RequestHeader(AUTH_TOKEN) String bearerToken, @RequestBody CreditosUsuarioPayload credito){
        return new ResponseEntity<ResponseObtenerCreditosPayload>(ResponseObtenerCreditosPayload.builder()
                .resultadoConsulta(Boolean.TRUE)
                .idRespuesta(Long.valueOf("1"))
                .descripcionRespuesta("Servicio consumido de forma exitosa")
                .build(), HttpStatus.OK);
    }
}

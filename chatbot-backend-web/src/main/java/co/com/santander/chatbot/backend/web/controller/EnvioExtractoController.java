package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.ResponseEnvioExtractoPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EnvioExtractoController {

    @PostMapping
    public ResponseEntity<ResponseEnvioExtractoPayload> generateExtract(@RequestHeader("Authorization") String bearerToken, @RequestBody EnvioExtractoPayload envioExtracto){
        return new ResponseEntity<>(ResponseEnvioExtractoPayload.builder()
                .resultadoEnvio(Boolean.TRUE)
                .emailOfuscado("XXXX.amaya@santander.co")
                .tipoCredito(1)
                .convenio("JEEP")
                .idRespuesta(0)
                .descripcionRespuesta("Servicio consumido de forma exitosa")
                .build(), HttpStatus.OK);
    }
}

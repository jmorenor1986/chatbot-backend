package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.EnvioExtractoService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.enviarextracto.response.ResponseExtractosDisponibles;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.ResponseEnvioExtractoPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/extractos-cliente")
public class EnvioExtractoController {

    private final EnvioExtractoService envioExtractoService;

    @Autowired
    public EnvioExtractoController(EnvioExtractoService envioExtractoService) {
        this.envioExtractoService = envioExtractoService;
    }

    @PostMapping("/consulta-meses-disponibles")
    public ResponseEntity<ResponseExtractosDisponibles> consultaMesesDisponibles(@RequestHeader("Authorization") String bearerToken, @RequestBody EnvioExtractoPayload envioExtracto){
        Optional<ResponseExtractosDisponibles> response = envioExtractoService.consultaDocumentos(bearerToken, ServiciosEnum.SERVICIO_TERMINOS_CONDICIONES, envioExtracto.getTelefono(), envioExtracto );
        if(response.isPresent()){
            return new ResponseEntity<>(response.get() ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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

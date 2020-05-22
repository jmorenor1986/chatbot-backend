package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.ConsultaExtractoService;
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

    private final ConsultaExtractoService consultaExtractoService;
    private final EnvioExtractoService envioExtractoService;

    @Autowired
    public EnvioExtractoController(ConsultaExtractoService consultaExtractoService, EnvioExtractoService envioExtractoService) {
        this.consultaExtractoService = consultaExtractoService;
        this.envioExtractoService = envioExtractoService;
    }

    @PostMapping("/consulta-meses-disponibles")
    public ResponseEntity<ResponseExtractosDisponibles> consultaMesesDisponibles(@RequestHeader("Authorization") String bearerToken, @RequestBody EnvioExtractoPayload envioExtracto){
        Optional<ResponseExtractosDisponibles> response = consultaExtractoService.consultaDocumentos(bearerToken, ServiciosEnum.SERVICIO_CONSULTA_EXTRACTOS, envioExtracto.getTelefono(), envioExtracto );
        if(response.isPresent()){
            return new ResponseEntity<>(response.get() ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/enviar-extracto")
    public ResponseEntity<ResponseEnvioExtractoPayload> generateExtract(@RequestHeader("Authorization") String bearerToken, @RequestBody EnvioExtractoPayload envioExtracto){
        Optional<ResponseEnvioExtractoPayload> response = envioExtractoService.envioExtracto(bearerToken, ServiciosEnum.SERVICIO_ENVIO_EXTRACTO, envioExtracto.getTelefono(), envioExtracto);
        if(response.isPresent()){
            return new ResponseEntity<>( response.get() ,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

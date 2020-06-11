package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.EnlacePseService;
import co.com.santander.chatbot.backend.web.service.MapperTelService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.enlacePse.EnlacePsePayload;
import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/enlacePse")
@Api(value = "Permite obtener el link para pagos por medio de PSE", tags = {"V1: Acceso a link de pagos permitidos por PSE"})
public class EnlacePseController {

    private final EnlacePseService enlacePseService;
    private final MapperTelService mapperTelService;


    @Autowired
    public EnlacePseController(EnlacePseService enlacePseService, MapperTelService mapperTelService) {
        this.enlacePseService = enlacePseService;
        this.mapperTelService = mapperTelService;
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Operacion exitosa}, {Error formato telefono}, {Error campo obligatorio}, {Error Número de verificador}"),
            @ApiResponse(code = 401, message = "{No autorizado para este recurso}"),
            @ApiResponse(code = 403, message = "{Peticion Sin token}, {Token expirado}")
    })
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEnlacePsePayload> getEnlacePse(@RequestHeader("Authorization") String bearerToken, @RequestBody EnlacePsePayload enlacePsePayload){
        enlacePsePayload.setTelefono(mapperTelService.mapTelDigits(enlacePsePayload.getTelefono()));
        Optional<ResponseEnlacePsePayload> response = enlacePseService.getEnlacePse(bearerToken, ServiciosEnum.SERVICIO_ENLACE_PSE,enlacePsePayload.getTelefono(), enlacePsePayload.getNumeroVerificador());
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

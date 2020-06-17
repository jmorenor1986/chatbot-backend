package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.ConsultaExtractoService;
import co.com.santander.chatbot.backend.web.service.EnvioExtractoService;
import co.com.santander.chatbot.backend.web.service.MapperTelService;
import co.com.santander.chatbot.backend.web.service.ValidateClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.enviarextracto.response.ResponseExtractosDisponibles;
import co.com.santander.chatbot.domain.payload.service.extracto.ConsultaExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.EnvioExtractoPayload;
import co.com.santander.chatbot.domain.payload.service.extracto.ResponseEnvioExtractoPayload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("v1/extractos-cliente")
@Api(value = "Acceso a la consulta y envío de extratos de vehiculo", tags = {"V1: Consulta y Envío por meses de extractos disponibles para el cliente"})
public class EnvioExtractoController {

    private final ConsultaExtractoService consultaExtractoService;
    private final EnvioExtractoService envioExtractoService;
    private final MapperTelService mapperTelService;
    private final ValidateClienteService validateClienteService;

    @Autowired
    public EnvioExtractoController(ConsultaExtractoService consultaExtractoService, EnvioExtractoService envioExtractoService, MapperTelService mapperTelService,  ValidateClienteService validateClienteService) {
        this.consultaExtractoService = consultaExtractoService;
        this.envioExtractoService = envioExtractoService;
        this.mapperTelService = mapperTelService;
        this.validateClienteService = validateClienteService;
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Operacion exitosa}, {Error formato telefono}, {Error campo obligatorio}, {Error Número de verificador}"),
            @ApiResponse(code = 401, message = "{No autorizado para este recurso}"),
            @ApiResponse(code = 403, message = "{Peticion Sin token}, {Token expirado}")
    })
    @PostMapping("/consulta-meses-disponibles")
    public ResponseEntity<ResponseExtractosDisponibles> consultaMesesDisponibles(@RequestHeader("Authorization") String bearerToken,@Valid @RequestBody ConsultaExtractoPayload consultaExtracto){
        consultaExtracto.setTelefono(mapperTelService.mapTelDigits(consultaExtracto.getTelefono()));
        validateClienteService.validateClient(bearerToken, consultaExtracto.getTelefono());
        Optional<ResponseExtractosDisponibles> response = consultaExtractoService.consultaDocumentos(bearerToken, ServiciosEnum.SERVICIO_CONSULTA_EXTRACTOS, consultaExtracto.getTelefono(), consultaExtracto );
        if(response.isPresent()){
            return new ResponseEntity<>(response.get() ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Operacion exitosa}, {Error formato telefono}, {Error campo obligatorio}, {Error Número de verificador}"),
            @ApiResponse(code = 401, message = "{No autorizado para este recurso}"),
            @ApiResponse(code = 403, message = "{Peticion Sin token}, {Token expirado}")
    })
    @PostMapping("/enviar-extracto")
    public ResponseEntity<ResponseEnvioExtractoPayload> generateExtract(@RequestHeader("Authorization") String bearerToken,@Valid @RequestBody EnvioExtractoPayload envioExtracto){
        envioExtracto.setTelefono(mapperTelService.mapTelDigits(envioExtracto.getTelefono()));
        validateClienteService.validateClient(bearerToken, envioExtracto.getTelefono());
        Optional<ResponseEnvioExtractoPayload> response = envioExtractoService.envioExtracto(bearerToken, ServiciosEnum.SERVICIO_ENVIO_EXTRACTO, envioExtracto.getTelefono(), envioExtracto);
        if(response.isPresent()){
            return new ResponseEntity<>( response.get() ,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

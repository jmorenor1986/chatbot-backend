package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.backend.web.service.MapperTelService;
import co.com.santander.chatbot.backend.web.service.ValidateClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ClientePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/validarCliente")
public class ValidarClienteController {

    private final ClienteService clienteService;
    private final MapperTelService mapperTelService;
    private final ValidateClienteService validateClienteService;

    public ValidarClienteController(@Qualifier("clienteServiceImpl") ClienteService clienteService, MapperTelService mapperTelService, ValidateClienteService validateClienteService) {
        this.clienteService = clienteService;
        this.mapperTelService = mapperTelService;
        this.validateClienteService = validateClienteService;
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Operacion exitosa}, {Error formato telefono}, {Error campo obligatorio}, {Error número de caracteres permitido}"),
            @ApiResponse(code = 401, message = "{No autorizado para este recurso}"),
            @ApiResponse(code = 403, message = "{Peticion Sin token}, {Token expirado}")
    })
    @PostMapping(value = "/")
    public ResponseEntity<ResponsePayload> validar(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody ClientePayload clientePayload) {
        clientePayload.setTelefono(mapperTelService.mapTelDigits(clientePayload.getTelefono()));
        validateClienteService.validateClient(bearerToken, clientePayload.getTelefono());
        ResponseEntity<ResponsePayload> response = clienteService.validarCliente(bearerToken, ServiciosEnum.SERVICIO_VALIDA_CLIENTE, clientePayload.getTelefono(), clientePayload);
        if(HttpStatus.OK.equals(response.getStatusCode())){
            ResponsePayload objResponse = response.getBody();
            objResponse.setDescripcionRespuesta("Servicio consumido de forma exitosa");
            return new ResponseEntity<>(objResponse, HttpStatus.OK);
        }else if(HttpStatus.NO_CONTENT.equals(response.getStatusCode())){
            ResponsePayload objResponse = ResponsePayload.builder()
                    .idRespuesta(1)
                    .resultadoValidacion(Boolean.FALSE)
                    .descripcionRespuesta("Redirigir con un agente")
                    .build();
            return new ResponseEntity<>(objResponse, HttpStatus.OK);
        }
        return response;
    }
}

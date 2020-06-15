package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.ClienteService;
import co.com.santander.chatbot.backend.web.service.MapperTelService;
import co.com.santander.chatbot.backend.web.service.ValidateClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.CreditosUsuarioPayload;
import co.com.santander.chatbot.domain.payload.service.obtenercreditos.ResponseObtenerCreditosPayload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("v1/obtenerCreditosUsuario")
@Api(value = "Obtener creditos para determinado cliente", tags = {"V1: Indica que posibles creditos pueden ser usados para determinado servicio"})
public class ObtenerCreditosUsuarioController {

    private final ClienteService clienteService;
    private final MapperTelService mapperTelService;
    private final ValidateClienteService validateClienteService;

    @Autowired
    public ObtenerCreditosUsuarioController(@Qualifier("proxyClienteServiceImpl") ClienteService clienteService, MapperTelService mapperTelService, ValidateClienteService validateClienteService) {
        this.clienteService = clienteService;
        this.mapperTelService = mapperTelService;
        this.validateClienteService = validateClienteService;
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Operacion exitosa}, {Error formato telefono}, {Error campo obligatorio}, {Error Número de verificador}"),
            @ApiResponse(code = 401, message = "{No autorizado para este recurso}"),
            @ApiResponse(code = 403, message = "{Peticion Sin token}, {Token expirado}")
    })
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObtenerCreditosPayload> obtener(@RequestHeader("Authorization") String bearerToken, @Valid @RequestBody CreditosUsuarioPayload credito) {
        credito.setTelefono(mapperTelService.mapTelDigits(credito.getTelefono()));
        validateClienteService.validateClient(bearerToken, credito.getTelefono());
        Optional<ResponseObtenerCreditosPayload> response = clienteService.obtenerCreditos(bearerToken, ServiciosEnum.SERVICIO_OBTENER_CREDITOS, credito.getTelefono(), credito);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
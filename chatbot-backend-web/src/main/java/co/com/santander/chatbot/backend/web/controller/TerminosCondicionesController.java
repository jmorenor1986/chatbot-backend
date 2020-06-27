package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.MapperTelService;
import co.com.santander.chatbot.backend.web.service.TerminosCondicionesService;
import co.com.santander.chatbot.backend.web.service.ValidateClienteService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.TerminosCondicionesPayload;
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
@RequestMapping("v1/terminos-condiciones")
@Api(value = "Registro de terminos y condiciones", tags = {"V1: Almacena el registro de terminos y condiciones"})
public class TerminosCondicionesController {

    private final TerminosCondicionesService terminosCondicionesService;
    private final MapperTelService mapperTelService;
    private final ValidateClienteService validateClienteService;

    @Autowired
    public TerminosCondicionesController(TerminosCondicionesService terminosCondicionesService, MapperTelService mapperTelService,  ValidateClienteService validateClienteService) {
        this.terminosCondicionesService = terminosCondicionesService;
        this.mapperTelService = mapperTelService;
        this.validateClienteService = validateClienteService;
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Operacion exitosa}, {Error formato telefono}, {Error campo obligatorio}"),
            @ApiResponse(code = 401, message = "{No autorizado para este recurso}"),
            @ApiResponse(code = 403, message = "{Peticion Sin token}, {Token expirado}")
    })
    @PostMapping("/")
    public ResponseEntity<TerminosCondicionesPayload> save(@RequestHeader("Authorization") String bearerToken,@Valid @RequestBody TerminosCondicionesPayload terminosCondiciones){
        terminosCondiciones.setTelefono(mapperTelService.mapTelDigits(terminosCondiciones.getTelefono()));
        Optional<TerminosCondicionesPayload> response = terminosCondicionesService.save(bearerToken, ServiciosEnum.SERVICIO_TERMINOS_CONDICIONES,terminosCondiciones.getTelefono().toString(), terminosCondiciones);
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

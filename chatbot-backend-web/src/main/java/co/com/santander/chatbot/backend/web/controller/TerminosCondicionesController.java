package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.MapperTelService;
import co.com.santander.chatbot.backend.web.service.TerminosCondicionesService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.TerminosCondicionesPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("v1/terminos-condiciones")
public class TerminosCondicionesController {

    private final TerminosCondicionesService terminosCondicionesService;
    private final MapperTelService mapperTelService;

    @Autowired
    public TerminosCondicionesController(TerminosCondicionesService terminosCondicionesService, MapperTelService mapperTelService) {
        this.terminosCondicionesService = terminosCondicionesService;
        this.mapperTelService = mapperTelService;
    }

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

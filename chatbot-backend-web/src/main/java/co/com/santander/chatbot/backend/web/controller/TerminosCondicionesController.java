package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.TerminosCondicionesService;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.TerminosCondicionesPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/terminos-condiciones")
public class TerminosCondicionesController {

    private final TerminosCondicionesService terminosCondicionesService;

    @Autowired
    public TerminosCondicionesController(TerminosCondicionesService terminosCondicionesService) {
        this.terminosCondicionesService = terminosCondicionesService;
    }

    @PostMapping("/")
    public ResponseEntity<TerminosCondicionesPayload> save(@RequestHeader("Authorization") String bearerToken, @RequestBody TerminosCondicionesPayload terminosCondiciones){
        Optional<TerminosCondicionesPayload> response = terminosCondicionesService.save(bearerToken, ServiciosEnum.SERVICIO_TERMINOS_CONDICIONES, terminosCondiciones);
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

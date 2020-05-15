package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.TerminosCondiciones;
import co.com.santander.chatbot.accesodatos.service.TerminosCondicionesService;
import co.com.santander.chatbot.domain.payload.accesodatos.TerminosCondicionesPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("v1/terminos-condiciones")
public class TerminosCondicionesController {

    private TerminosCondicionesService terminosCondicionesService;
    private ModelMapper mapper;

    @Autowired
    public TerminosCondicionesController(TerminosCondicionesService terminosCondicionesService, ModelMapper mapper) {
        this.terminosCondicionesService = terminosCondicionesService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TerminosCondicionesPayload> save(@RequestBody TerminosCondicionesPayload terminosCondiciones){
        Optional<TerminosCondiciones> response = terminosCondicionesService.save(mapper.map(terminosCondiciones, TerminosCondiciones.class));
        if(response.isPresent()){
            return new ResponseEntity<>(mapper.map(response.get(), TerminosCondicionesPayload.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

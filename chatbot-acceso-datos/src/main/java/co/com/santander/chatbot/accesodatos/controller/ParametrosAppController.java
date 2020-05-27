package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.ParametrosApp;
import co.com.santander.chatbot.accesodatos.service.ParametrosAppService;
import co.com.santander.chatbot.domain.payload.accesodatos.ParametrosAppPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("v1/parametros-app")
public class ParametrosAppController {

    private final ParametrosAppService parametrosAppService;
    private final ModelMapper modelMapper;

    @Autowired
    public ParametrosAppController(ParametrosAppService parametrosAppService, ModelMapper modelMapper) {
        this.parametrosAppService = parametrosAppService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/")
    public ResponseEntity<ParametrosAppPayload> getByClave(@RequestParam(value = "clave") String clave){
        Optional<ParametrosApp> response = parametrosAppService.findByClave(clave);
        if(response.isPresent()){
            return new ResponseEntity<>(modelMapper.map( response.get(), ParametrosAppPayload.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

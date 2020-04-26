package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.service.LogClienteService;
import co.com.santander.chatbot.domain.common.utilities.GenericLogPayload;
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
@RequestMapping("v1/log")
public class LogClienteController {

    private final LogClienteService logClienteService;

    @Autowired
    public LogClienteController(LogClienteService logClienteService) {
        this.logClienteService = logClienteService;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertarLog(@RequestBody GenericLogPayload genericLogPayload){
        Optional<Boolean> insert = logClienteService.saveLogService(genericLogPayload);
        if(insert.isPresent()){
            return new ResponseEntity<>(insert.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

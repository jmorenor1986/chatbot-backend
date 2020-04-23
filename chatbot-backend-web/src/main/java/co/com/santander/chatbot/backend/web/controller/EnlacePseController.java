package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.service.EnlacePseService;
import co.com.santander.chatbot.domain.payload.service.enlacePse.EnlacePsePayload;
import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/enlacePse")
public class EnlacePseController {

    private final EnlacePseService enlacePseService;

    @Autowired
    public EnlacePseController(EnlacePseService enlacePseService) {
        this.enlacePseService = enlacePseService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEnlacePsePayload> getEnlacePse(@RequestHeader("Authorization") String bearerToken, @RequestBody EnlacePsePayload enlacePsePayload){
        Optional<ResponseEnlacePsePayload> response = enlacePseService.getEnlacePse(bearerToken, enlacePsePayload.getTelefono(), enlacePsePayload.getNumeroVerificador());
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

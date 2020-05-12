package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.PseParam;
import co.com.santander.chatbot.accesodatos.service.PseParamService;
import co.com.santander.chatbot.domain.payload.accesodatos.PseParamPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/pse-param")
public class PseParamController {

    private final PseParamService pseParamService;
    private final ModelMapper map;

    @Autowired
    public PseParamController(PseParamService pseParamService, ModelMapper map) {
        this.pseParamService = pseParamService;
        this.map = map;
    }

    @GetMapping(value = "/")
    public ResponseEntity<PseParamPayload> getByIdBancoAndTipoCredito(@RequestParam(value = "idBanco") Long idBanco, @RequestParam(value = "tipoCredito") Long tipoCredito ){
        Optional<PseParam> pseParam = pseParamService.findByIdBancoAndTipoCredito(idBanco, tipoCredito);
        if(pseParam.isPresent()){
            PseParamPayload responsePsePayload = map.map( pseParam.get(), PseParamPayload.class );
            return new ResponseEntity<>(responsePsePayload, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
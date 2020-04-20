package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.InfoWhatsAppWS;
import co.com.santander.chatbot.accesodatos.service.InfoWhatsAppWSService;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("v1/infowhatsappwscontroller")
public class InfoWhatsAppWSController {

    private final InfoWhatsAppWSService infoWhatsAppWSService;
    private final ModelMapper modelMapper;

    @Autowired
    public InfoWhatsAppWSController(InfoWhatsAppWSService infoWhatsAppWSService, ModelMapper modelMapper) {
        this.infoWhatsAppWSService = infoWhatsAppWSService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfoWhatsAppWSPayload> save(@RequestBody InfoWhatsAppWSPayload infoWhatsAppWSPayload) {
        InfoWhatsAppWS entity = modelMapper.map(infoWhatsAppWSPayload, InfoWhatsAppWS.class);
        Optional<InfoWhatsAppWS> response = infoWhatsAppWSService.saveEntity(entity);
        if(response.isPresent()){
            return new ResponseEntity<InfoWhatsAppWSPayload>(modelMapper.map(response.get(), InfoWhatsAppWSPayload.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(value = "/validateexistingprocess/")
    public ResponseEntity<Boolean> validateExistingProcess(@RequestParam(value = "numCreditoBanco") String numCreditoBanco
            , @RequestParam(value = "numeroIdentificacion") String numeroIdentificacion
            , @RequestParam(value = "numPeticionServicio") Long numPeticionServicio
    ) {
        Optional<Boolean> respuesta = infoWhatsAppWSService.validateExistingProcess(numCreditoBanco, numeroIdentificacion, numPeticionServicio);
        if(respuesta.isPresent()){
            return new ResponseEntity<Boolean>(respuesta.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

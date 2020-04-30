package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.InfoWhatsAppWS;
import co.com.santander.chatbot.accesodatos.service.ClienteService;
import co.com.santander.chatbot.accesodatos.service.InfoWhatsAppWSService;
import co.com.santander.chatbot.domain.payload.accesodatos.InfoWhatsAppWSPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("v1/infowhatsappwscontroller")
public class InfoWhatsAppWSController {

    private final InfoWhatsAppWSService infoWhatsAppWSService;
    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    @Autowired
    public InfoWhatsAppWSController(InfoWhatsAppWSService infoWhatsAppWSService, ClienteService clienteService, ModelMapper modelMapper) {
        this.infoWhatsAppWSService = infoWhatsAppWSService;
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfoWhatsAppWSPayload> save(@RequestBody InfoWhatsAppWSPayload infoWhatsAppWSPayload) {
        //Valido la existencia de los datos
        Optional<Boolean>  validate = clienteService.validaCreditoByCedula(infoWhatsAppWSPayload.getNumeroIdentificacion(), infoWhatsAppWSPayload.getNumCreditoBanco());
        if(validate.isPresent() && Boolean.FALSE.equals(validate.get())){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }else if(validate.isPresent() && Boolean.TRUE.equals(validate.get()) ){
            Optional<String> cedula = clienteService.findCedulaByCedulaAndCredito(infoWhatsAppWSPayload.getNumeroIdentificacion(), infoWhatsAppWSPayload.getNumCreditoBanco());
            if(cedula.isPresent()){
                InfoWhatsAppWS entity = modelMapper.map(infoWhatsAppWSPayload, InfoWhatsAppWS.class);
                entity.setNumeroIdentificacion(cedula.get());
                Optional<InfoWhatsAppWS> response = infoWhatsAppWSService.saveEntity(entity);
                if (response.isPresent()) {
                    return new ResponseEntity<>(modelMapper.map(response.get(), InfoWhatsAppWSPayload.class), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(value = "/validateexistingprocess/")
    public ResponseEntity<InfoWhatsAppWSPayload> validateExistingProcess(@RequestParam(value = "numCreditoBanco") String numCreditoBanco
            , @RequestParam(value = "numeroIdentificacion") String numeroIdentificacion
            , @RequestParam(value = "numPeticionServicio") Long numPeticionServicio
    ) {
        List<InfoWhatsAppWS> respuesta = infoWhatsAppWSService.validateExistingProcess(numCreditoBanco, numeroIdentificacion, numPeticionServicio);
        if (!respuesta.isEmpty()) {
            return new ResponseEntity<>(modelMapper.map(respuesta.get(0), InfoWhatsAppWSPayload.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

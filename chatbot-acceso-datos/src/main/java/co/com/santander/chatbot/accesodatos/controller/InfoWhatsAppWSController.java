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

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("v1/usuarioapp")
public class InfoWhatsAppWSController  {

    private final InfoWhatsAppWSService infoWhatsAppWSService;
    private final ModelMapper modelMapper;

    @Autowired
    public InfoWhatsAppWSController(InfoWhatsAppWSService infoWhatsAppWSService, ModelMapper modelMapper) {
        this.infoWhatsAppWSService = infoWhatsAppWSService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfoWhatsAppWSPayload> save(@RequestBody InfoWhatsAppWSPayload infoWhatsAppWSPayload){
        //Mapeo del payload a la entidad
        InfoWhatsAppWS entity = modelMapper.map(infoWhatsAppWSPayload, InfoWhatsAppWS.class);
        Optional<InfoWhatsAppWS> response = infoWhatsAppWSService.saveEntity(entity);
        return new ResponseEntity<InfoWhatsAppWSPayload>( modelMapper.map(response.get(), InfoWhatsAppWSPayload.class), HttpStatus.OK);
    }

    @GetMapping(value = "/validateexistingprocess/?numCreditoBanco={numCreditoBanco}&numeroIdentificacion={numeroIdentificacion}&numPeticionServicio={numPeticionServicio}")
    public ResponseEntity<Boolean> validateExistingProcess(@PathVariable(value = "numCreditoBanco")      String numCreditoBanco,
                                                           @PathVariable(value = "numeroIdentificacion") String numeroIdentificacion,
                                                           @PathVariable(value = "numPeticionServicio") Long numPeticionServicio){
        Optional<Boolean> respuesta = infoWhatsAppWSService.validateExistingProcess(numCreditoBanco,numeroIdentificacion,numPeticionServicio);
        return new ResponseEntity<Boolean>(respuesta.get(), HttpStatus.OK);
    }
}

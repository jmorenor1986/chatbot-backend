package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.IdDocumento;
import co.com.santander.chatbot.accesodatos.service.IdDocumentoService;
import co.com.santander.chatbot.domain.payload.accesodatos.documento.IdDocumentoPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/id-documento")
public class IdDocumentoController {

    private IdDocumentoService idDocumentoService;
    private ModelMapper mapper;

    @Autowired
    public IdDocumentoController(IdDocumentoService idDocumentoService, ModelMapper mapper) {
        this.idDocumentoService = idDocumentoService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<IdDocumentoPayload> save(@RequestBody IdDocumentoPayload idDocumentoPayload){
        IdDocumentoPayload documento = mapper.map(idDocumentoService.save(mapper.map(idDocumentoPayload, IdDocumento.class)), IdDocumentoPayload.class);
        return new ResponseEntity<>(documento, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<IdDocumentoPayload> getDocumentById(@PathVariable("id") Long id){
        Optional<IdDocumento> response = idDocumentoService.findById(id);
        if(response.isPresent()){
            return new ResponseEntity<>(mapper.map(response.get(), IdDocumentoPayload.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

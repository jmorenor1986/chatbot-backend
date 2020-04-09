package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.entity.UsuarioApp;
import co.com.santander.chatbot.accesodatos.service.UsuarioAppService;
import co.com.santander.chatbot.domain.payload.accesodatos.UsuarioAppPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("v1/usuarioapp")
public class UsuarioAppController {

    private final UsuarioAppService usuarioAppService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioAppController(UsuarioAppService usuarioAppService, ModelMapper modelMapper) {
        this.usuarioAppService = usuarioAppService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/" )
    public ResponseEntity<UsuarioAppPayload> createUser(@RequestBody UsuarioAppPayload usuarioPayload){
        UsuarioApp usuarioEntity = modelMapper.map(usuarioPayload, UsuarioApp.class);
        UsuarioAppPayload usuarioAppPayload = modelMapper.map(usuarioAppService.createUser(usuarioEntity), UsuarioAppPayload.class);
        usuarioAppPayload.setContra("");
        return new ResponseEntity<UsuarioAppPayload>(usuarioAppPayload, HttpStatus.OK);
    }

    @PostMapping(value = "/login/" )
    public ResponseEntity<Boolean> validateUser(@RequestBody UsuarioAppPayload usuarioPayload){
        Optional<Boolean> usuario =  usuarioAppService.validateLoginUser(usuarioPayload.getUsuario(), usuarioPayload.getContra());
        if(usuario.isPresent() && usuario.get()){
            return new ResponseEntity<Boolean>(usuario.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
    }
}

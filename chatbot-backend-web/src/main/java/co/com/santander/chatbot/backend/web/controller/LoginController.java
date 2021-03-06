package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.dto.TokenDto;
import co.com.santander.chatbot.backend.web.dto.UsuarioDto;
import co.com.santander.chatbot.backend.web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UsuarioService usuarioService;

    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@RequestBody UsuarioDto usuarioDto) {
        Optional<TokenDto> token = usuarioService.generaToken();
        if(!token.isPresent()) {
            return new ResponseEntity<>(TokenDto.builder().mensaje("Error al generar el token").build(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<TokenDto>(token.get(), HttpStatus.OK);
    }
}

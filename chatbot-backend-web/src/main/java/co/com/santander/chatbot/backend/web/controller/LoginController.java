package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.exceptions.CustomAuthenticationException;
import co.com.santander.chatbot.backend.web.service.UsuarioService;
import co.com.santander.chatbot.domain.dto.security.TokenDto;
import co.com.santander.chatbot.domain.dto.security.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/login")
public class LoginController {

    private final UsuarioService usuarioService;

    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@Valid @RequestBody UsuarioDto usuarioDto) throws CustomAuthenticationException {
        Optional<TokenDto> token = usuarioService.generaToken(usuarioDto.getCorreo(), usuarioDto.getContrasena());
        if (!token.isPresent()) {
            return new ResponseEntity<>(TokenDto.builder().mensaje("Error al generar el token").build(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(token.get(), HttpStatus.OK);
    }
}

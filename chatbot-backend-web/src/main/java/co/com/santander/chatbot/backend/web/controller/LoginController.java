package co.com.santander.chatbot.backend.web.controller;

import co.com.santander.chatbot.backend.web.exceptions.CustomAuthenticationException;
import co.com.santander.chatbot.backend.web.service.UsuarioService;
import co.com.santander.chatbot.domain.dto.security.TokenDto;
import co.com.santander.chatbot.domain.dto.security.UsuarioDto;
import io.swagger.annotations.*;
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
@Api(value = "Permite a los usuarios el acceso a los demas servicios", tags = {"V1: Versión basica para el login"})
public class LoginController {

    private final UsuarioService usuarioService;

    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ApiOperation(value = "Metodo de solicitud de token", response = TokenDto.class, consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "{Acceso correcto}, {Error formato correo}, {Error campo obligatorio}"),
            @ApiResponse(code = 401, message = "Usuario y contrasenia incorrecta"),
    })
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@Valid @RequestBody UsuarioDto usuarioDto) throws CustomAuthenticationException {
        Optional<TokenDto> token = usuarioService.generaToken(usuarioDto.getCorreo(), usuarioDto.getContrasena());
        if (!token.isPresent()) {
            return new ResponseEntity<>(TokenDto.builder().mensaje("Error al generar el token").build(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(token.get(), HttpStatus.OK);
    }
}

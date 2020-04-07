package co.com.santander.chatbot.accesodatos.service;

import java.util.Optional;

public interface UsuarioService {

    Optional<Boolean> consultarUsuario(Long aLong, String s);
}

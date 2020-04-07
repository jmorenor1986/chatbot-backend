package co.com.santander.chatbot.accesodatos.service;

import java.math.BigInteger;
import java.util.Optional;

public interface UsuarioService {

    Optional<Boolean> consultarUsuario(BigInteger aLong, String s);
}

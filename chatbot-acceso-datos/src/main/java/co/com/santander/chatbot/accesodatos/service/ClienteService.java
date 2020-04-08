package co.com.santander.chatbot.accesodatos.service;

import java.math.BigInteger;
import java.util.Optional;

public interface ClienteService {

    Optional<Boolean> consultarCliente(BigInteger aLong, String s);
}

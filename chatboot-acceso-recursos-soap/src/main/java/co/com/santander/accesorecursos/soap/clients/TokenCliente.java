package co.com.santander.accesorecursos.soap.clients;

import co.com.santander.chatbot.domain.payload.enviarextracto.TokenPayload;

public interface TokenCliente {

    String generarToken(TokenPayload user);
}

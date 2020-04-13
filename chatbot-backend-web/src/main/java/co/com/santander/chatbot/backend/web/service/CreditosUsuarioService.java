package co.com.santander.chatbot.backend.web.service;

import co.com.santander.chatbot.domain.dto.accesorecursos.RespuestaDto;

import java.util.Optional;

public interface CreditosUsuarioService {

    Optional<RespuestaDto> consultarCreditosUsuario(String telefono, int tipoOperacion);
}

package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.IdDocumento;

import java.util.Optional;

public interface IdDocumentoService {

    IdDocumento save(IdDocumento documento);

    Optional<IdDocumento> findById(Long id);
}

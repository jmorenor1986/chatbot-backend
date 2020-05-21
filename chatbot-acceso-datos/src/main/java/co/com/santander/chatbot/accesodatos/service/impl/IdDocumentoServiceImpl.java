package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.IdDocumento;
import co.com.santander.chatbot.accesodatos.repository.IdDocumentoRepository;
import co.com.santander.chatbot.accesodatos.service.IdDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdDocumentoServiceImpl implements IdDocumentoService {

    private IdDocumentoRepository idDocumentoRepository;

    @Autowired
    public IdDocumentoServiceImpl(IdDocumentoRepository idDocumentoRepository) {
        this.idDocumentoRepository = idDocumentoRepository;
    }

    @Override
    public IdDocumento save(IdDocumento documento) {
        return idDocumentoRepository.save(documento);
    }
}

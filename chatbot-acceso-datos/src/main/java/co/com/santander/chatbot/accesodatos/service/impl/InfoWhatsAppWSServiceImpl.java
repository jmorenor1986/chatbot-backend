package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.InfoWhatsAppWS;
import co.com.santander.chatbot.accesodatos.repository.InfoWhatsAppWSRepository;
import co.com.santander.chatbot.accesodatos.service.InfoWhatsAppWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoWhatsAppWSServiceImpl implements InfoWhatsAppWSService {

    private final InfoWhatsAppWSRepository infoWhatsAppWSRepository;

    @Autowired
    public InfoWhatsAppWSServiceImpl(InfoWhatsAppWSRepository infoWhatsAppWSRepository) {
        this.infoWhatsAppWSRepository = infoWhatsAppWSRepository;
    }

    @Override
    public Optional<InfoWhatsAppWS> saveEntity(InfoWhatsAppWS entity) {
        InfoWhatsAppWS respuesta = infoWhatsAppWSRepository.save(entity);
        return Optional.of(respuesta);
    }

    @Override
    public List<InfoWhatsAppWS> validateExistingProcess(String numCreditoBanco, String numeroIdentificacion, Long numPeticionServicio) {
        return infoWhatsAppWSRepository.findByNumCreditoBancoAndNumeroIdentificacionAndNumPeticionServicio(numCreditoBanco, numeroIdentificacion, numPeticionServicio);
    }

}

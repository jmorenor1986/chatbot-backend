package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.InfoWhatsAppWS;

import java.util.List;
import java.util.Optional;

public interface InfoWhatsAppWSService {

    Optional<InfoWhatsAppWS> saveEntity(InfoWhatsAppWS entity);

    List<InfoWhatsAppWS> validateExistingProcess(String numCreditoBanco, String numeroIdentificacion, Long numPeticionServicio);
}

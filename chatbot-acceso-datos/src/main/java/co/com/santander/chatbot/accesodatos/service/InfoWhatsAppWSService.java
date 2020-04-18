package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.InfoWhatsAppWS;

import java.util.Optional;

public interface InfoWhatsAppWSService {

    Optional<InfoWhatsAppWS> saveEntity(InfoWhatsAppWS entity);

    Optional<Boolean> validateExistingProcess(String numCreditoBanco, String numeroIdentificacion, Long numPeticionServicio);
}

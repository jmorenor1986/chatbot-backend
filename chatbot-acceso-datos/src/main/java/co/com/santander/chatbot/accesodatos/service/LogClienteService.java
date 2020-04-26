package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.domain.common.utilities.GenericLogPayload;

import java.util.Optional;

public interface LogClienteService {

    Optional<Boolean> saveLogService(GenericLogPayload logEntity);
}

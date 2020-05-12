package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.PseParam;

import java.util.Optional;

public interface PseParamService {

    Optional<PseParam> findByIdBancoAndTipoCredito(Long idBanco, Long tipoCredito);
}

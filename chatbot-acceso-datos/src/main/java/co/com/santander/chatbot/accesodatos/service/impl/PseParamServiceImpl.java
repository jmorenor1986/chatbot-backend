package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.PseParam;
import co.com.santander.chatbot.accesodatos.repository.PseParamRepository;
import co.com.santander.chatbot.accesodatos.service.PseParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PseParamServiceImpl implements PseParamService {

    private final PseParamRepository pseParamRepository;

    @Autowired
    public PseParamServiceImpl(PseParamRepository pseParamRepository) {
        this.pseParamRepository = pseParamRepository;
    }

    @Override
    public Optional<PseParam> findByIdBancoAndTipoCredito(Long idBanco, Long tipoCredito) {
        return pseParamRepository.findByIdBancoAndTipoCredito(idBanco, tipoCredito);
    }
}

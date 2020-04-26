package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.Canal;
import co.com.santander.chatbot.accesodatos.entity.Log;
import co.com.santander.chatbot.accesodatos.entity.Servicio;
import co.com.santander.chatbot.accesodatos.repository.LogClienteRepository;
import co.com.santander.chatbot.accesodatos.repository.ServicioRepository;
import co.com.santander.chatbot.accesodatos.service.LogClienteService;
import co.com.santander.chatbot.domain.common.utilities.GenericLogPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LogClienteServiceImpl implements LogClienteService {

    private LogClienteRepository logClienteRepository;
    private ServicioRepository servicioRepository;
    private ModelMapper mapper;

    @Autowired
    public LogClienteServiceImpl(LogClienteRepository logClienteRepository,ServicioRepository servicioRepository , ModelMapper mapper) {
        this.logClienteRepository = logClienteRepository;
        this.servicioRepository = servicioRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Boolean> saveLogService(GenericLogPayload genericLogPayload) {
        //Mapeo la entidad
        Log logEntity = mapper.map(genericLogPayload,Log.class);
        logEntity.setCanal(Canal.builder().id(1L).build());
        logEntity.setFeha(new Date());
        //Busco el id del servicio que corresponde
        Optional<Servicio> servicio = servicioRepository.findByNombre(genericLogPayload.getServiciosEnum().name());
        if(servicio.isPresent()){
            logEntity.setServicio(servicio.get());
             logClienteRepository.save(logEntity);
            return Optional.of(Boolean.TRUE);
        }
        return Optional.of(Boolean.FALSE);
    }
}

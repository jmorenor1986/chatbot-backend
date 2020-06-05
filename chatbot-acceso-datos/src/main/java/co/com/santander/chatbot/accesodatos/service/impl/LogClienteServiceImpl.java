package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.Canal;
import co.com.santander.chatbot.accesodatos.entity.Cliente;
import co.com.santander.chatbot.accesodatos.entity.Log;
import co.com.santander.chatbot.accesodatos.entity.Servicio;
import co.com.santander.chatbot.accesodatos.repository.ClienteRepository;
import co.com.santander.chatbot.accesodatos.repository.LogClienteRepository;
import co.com.santander.chatbot.accesodatos.repository.ServicioRepository;
import co.com.santander.chatbot.accesodatos.service.LogClienteService;
import co.com.santander.chatbot.domain.common.utilities.GenericLogPayload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LogClienteServiceImpl implements LogClienteService {

    private LogClienteRepository logClienteRepository;
    private ServicioRepository servicioRepository;
    private ClienteRepository clienteRepository;
    private ModelMapper mapper;

    @Autowired
    public LogClienteServiceImpl(LogClienteRepository logClienteRepository,
                                 ServicioRepository servicioRepository,
                                 ClienteRepository clienteRepository,
                                 ModelMapper mapper) {
        this.logClienteRepository = logClienteRepository;
        this.servicioRepository = servicioRepository;
        this.clienteRepository = clienteRepository;
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
            logEntity = buscaAdicionalesEntidad(logEntity);
            logClienteRepository.save(logEntity);
            return Optional.of(Boolean.TRUE);
        }
        return Optional.of(Boolean.FALSE);
    }

    private Log buscaAdicionalesEntidad(Log logEntity){
        List<Cliente> clientes;
        //En el caso de que sea nulo el telefono quiere decir que esta solicitando un certificado y se busca el cliente
        //por medio de los ultimos 4 digitos de la cedula y el numero de credito
        if(Objects.isNull(logEntity.getTelefono())){
            clientes = clienteRepository.findByCedulaEndingWithAndNumerCredito(logEntity.getIdentificacion(), logEntity.getCredito());
        }else{
            clientes = clienteRepository.findByTelefono(logEntity.getTelefono());
        }
        if(!clientes.isEmpty()){
            if(Objects.isNull(logEntity.getTelefono())){
                logEntity.setTelefono(clientes.get(0).getTelefono());
                logEntity.setIdentificacion(clientes.get(0).getCedula());
            }else{
                logEntity.setIdentificacion(clientes.get(0).getCedula());
            }
            logEntity.setCorreo(clientes.get(0).getEmail());
        }
        return logEntity;
    }
}

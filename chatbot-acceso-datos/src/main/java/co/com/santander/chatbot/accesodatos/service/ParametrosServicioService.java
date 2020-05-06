package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.accesodatos.entity.ParametrosServicio;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;

import java.util.Date;
import java.util.Optional;

public interface ParametrosServicioService {
    ResponsePayload validarSolicitud(String canal, String servicio, Date fecha);

    Optional<ParametrosServicio> findByServicio(ServiciosEnum servicio);
}

package co.com.santander.chatbot.accesodatos.service;

import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;

import java.util.Date;

public interface ParametrosServicioService {
    ResponsePayload validarSolicitud(String canal, String servicio, Date fecha);
}

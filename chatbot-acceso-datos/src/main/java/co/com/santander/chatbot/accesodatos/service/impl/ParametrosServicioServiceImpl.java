package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.ParametrosServicio;
import co.com.santander.chatbot.accesodatos.repository.ParametrosServicioRepository;
import co.com.santander.chatbot.accesodatos.service.ParametrosServicioService;
import co.com.santander.chatbot.domain.common.utilities.DateUtilities;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParametrosServicioServiceImpl implements ParametrosServicioService {
    private final ParametrosServicioRepository parametrosServicioRepository;

    public ParametrosServicioServiceImpl(ParametrosServicioRepository parametrosServicioRepository) {
        this.parametrosServicioRepository = parametrosServicioRepository;
    }

    @Override
    public ResponsePayload validarSolicitud(String canal, String servicio, Date fecha) {
        List<ParametrosServicio> resultRepository = parametrosServicioRepository.findByNameService(servicio);
        if (resultRepository.isEmpty())
            throw new ValidateStateCertificateException("No hay parametros para consultar en el servicio ");
        if (validarHoraSolicitud(fecha, new Date(), resultRepository.get(0).getTiempoIntentos()))
            return ResponsePayload.builder()
                    .resultadoValidacion(Boolean.TRUE)
                    .idRespuesta(0)
                    .descripcionRespuesta("Puede realizar la solicitud")
                    .build();
        throw new ValidateStateCertificateException("No se puede realizar la solicitud, por favor intente más tarde");
    }

    private Boolean validarHoraSolicitud(Date fechaUltimaConsulta, Date fechaConsultaActual, int minutos) {
        return fechaConsultaActual.compareTo(DateUtilities.sumMinutesToDate(fechaUltimaConsulta, minutos)) > 0;
    }
}

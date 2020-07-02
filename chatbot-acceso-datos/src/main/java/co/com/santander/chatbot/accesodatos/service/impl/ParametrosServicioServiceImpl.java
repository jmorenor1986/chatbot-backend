package co.com.santander.chatbot.accesodatos.service.impl;

import co.com.santander.chatbot.accesodatos.entity.ParametrosServicio;
import co.com.santander.chatbot.accesodatos.repository.ParametrosServicioRepository;
import co.com.santander.chatbot.accesodatos.service.ParametrosServicioService;
import co.com.santander.chatbot.domain.common.utilities.DateUtilities;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.validators.exceptions.ValidateStateCertificateException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParametrosServicioServiceImpl implements ParametrosServicioService {
    private final ParametrosServicioRepository parametrosServicioRepository;

    public ParametrosServicioServiceImpl(ParametrosServicioRepository parametrosServicioRepository) {
        this.parametrosServicioRepository = parametrosServicioRepository;
    }

    @Override
    public ResponsePayload validarSolicitud(String canal, String servicio, Date fecha) {
        List<ParametrosServicio> resultRepository = parametrosServicioRepository.findByNameService( ServiciosEnum.findEnum(servicio).name());
        if (resultRepository.isEmpty()){
            throw new ValidateStateCertificateException("No hay parametros para consultar en el servicio ", 0L);
        }
        Boolean valida = validarHoraSolicitud(fecha, new Date(), resultRepository.get(0).getTiempoIntentos());
        if (Boolean.TRUE.equals(valida)) {
            return ResponsePayload.builder()
                    .resultado(Boolean.TRUE)
                    .idRespuesta(0)
                    .descripcionRespuesta("Puede realizar la solicitud")
                    .build();
        }else{
            return ResponsePayload.builder()
                    .resultado(Boolean.FALSE)
                    .idRespuesta(1)
                    .descripcionRespuesta("No puede realizar la solicitud de nuevo tiene una pendiente de procesar")
                    .build();
        }
    }

    @Override
    public Optional<ParametrosServicio> findByServicio(ServiciosEnum servicio) {
        List<ParametrosServicio> resultRepository = parametrosServicioRepository.findByNameService( servicio.name() );
        if(resultRepository.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(resultRepository.get(0));
    }

    private Boolean validarHoraSolicitud(Date fechaUltimaConsulta, Date fechaConsultaActual, int minutos) {
        return fechaConsultaActual.compareTo(DateUtilities.sumMinutesToDate(fechaUltimaConsulta, minutos)) > 0;
    }


}

package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.service.ParametrosServicioService;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import co.com.santander.chatbot.domain.payload.accesodatos.ValidarProcesoPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/parametros-servicio")
public class ParametrosServicioController {
    private final ParametrosServicioService parametrosServicioService;

    public ParametrosServicioController(ParametrosServicioService parametrosServicioService) {
        this.parametrosServicioService = parametrosServicioService;
    }

    @PostMapping("/consultar-proceso")
    public ResponseEntity<ResponsePayload> consultarProceso(@RequestBody ValidarProcesoPayload validarProcesoPayload) {

        return new ResponseEntity<>(parametrosServicioService.validarSolicitud(validarProcesoPayload.getCanal(),
                validarProcesoPayload.getServicio(), validarProcesoPayload.getFechaUltimaSolicitud()), HttpStatus.OK);

    }
}

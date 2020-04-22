package co.com.santander.chatbot.accesodatos.controller;

import co.com.santander.chatbot.accesodatos.service.ParametrosServicioService;
import co.com.santander.chatbot.domain.payload.accesodatos.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("v1/parametros-servicio")
public class ParametrosServicioController {
    private final ParametrosServicioService parametrosServicioService;

    public ParametrosServicioController(ParametrosServicioService parametrosServicioService) {
        this.parametrosServicioService = parametrosServicioService;
    }

    @GetMapping("/consultar-proceso")
    public ResponseEntity<ResponsePayload> consultarProceso(@RequestParam(value = "servicio") String servicio,
                                                            @RequestParam(value = "canal") String canal,
                                                            @RequestParam(value = "fechaUltimaSolicitud") Date fechaUltimaSolicitud) {

        return new ResponseEntity<>(parametrosServicioService.validarSolicitud(canal, servicio, fechaUltimaSolicitud), HttpStatus.OK);

    }
}

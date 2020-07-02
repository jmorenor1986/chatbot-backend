package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.common.utilities.StringUtilities;
import co.com.santander.chatbot.backend.web.service.FormatoMonedaService;
import co.com.santander.chatbot.backend.web.service.ParametrosAppService;
import co.com.santander.chatbot.domain.payload.service.enlacePse.ResponseEnlacePsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormatoMonedaServiceImpl implements FormatoMonedaService {

    private final ParametrosAppService parametrosAppService;

    @Autowired
    public FormatoMonedaServiceImpl(ParametrosAppService parametrosAppService) {
        this.parametrosAppService = parametrosAppService;
    }

    private String generarMascara(String token){
        Optional<String> responseParam = parametrosAppService.getParamByKey(token, "FORMATO_MONEDA");
        if(responseParam.isPresent()){
            return responseParam.get();
        }else{
            return "#,###.###";
        }
    }

    @Override
    public ResponseEnlacePsePayload currencyFormat(String token, ResponseEnlacePsePayload response) {
        response.setValorMora(StringUtilities.formatoMoneda(generarMascara(token), Long.valueOf(response.getValorMora())));
        response.setValorPagar(StringUtilities.formatoMoneda(generarMascara(token), Long.valueOf(response.getValorPagar())));
        response.setValorTotal(StringUtilities.formatoMoneda(generarMascara(token), Long.valueOf(response.getValorTotal())));
        return response;
    }

}

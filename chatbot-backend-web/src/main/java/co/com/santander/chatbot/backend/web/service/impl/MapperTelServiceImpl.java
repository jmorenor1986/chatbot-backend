package co.com.santander.chatbot.backend.web.service.impl;

import co.com.santander.chatbot.backend.web.service.MapperTelService;
import org.springframework.stereotype.Component;

@Component
public class MapperTelServiceImpl implements MapperTelService {
    @Override
    public String mapTelDigits(String telefono) {
        int tamanio = telefono.length();
        if (tamanio == 12 && telefono.startsWith("57")) {
            telefono = telefono.substring(2, tamanio);
        }
        return telefono;
    }
}

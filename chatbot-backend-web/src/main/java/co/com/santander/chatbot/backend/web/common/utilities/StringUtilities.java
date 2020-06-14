package co.com.santander.chatbot.backend.web.common.utilities;

import co.com.santander.chatbot.domain.common.utilities.SecurityUtilities;
import co.com.santander.chatbot.domain.dto.aspects.CommonAspectDto;
import co.com.santander.chatbot.domain.enums.ServiciosEnum;
import co.com.santander.chatbot.domain.payload.service.certificados.CertificadoPayload;

import java.util.Objects;

public class StringUtilities {

    private StringUtilities() {
        throw new IllegalStateException("StringUtilities class");
    }

    public static String ofuscarString(String texto, int caracteres) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            if (i >= texto.length() - caracteres) {
                break;
            }
            result.append("X");
        }
        result.append(texto.substring(texto.length() - caracteres));
        return result.toString();

    }

    public static String ofuscarCorreo(String correo, int caracteres) {
        String[] result = correo.split("@");
        return ofuscarString(result[0], caracteres).concat("@").concat(result[1]);
    }


    public static String ofuscarCredito(String credito) {
        if (Objects.nonNull(credito) && credito.length() > 5) {
            StringBuilder resultado = new StringBuilder();
            int tamanio = credito.length();
            for (int i = 0; i < tamanio; i++) {
                resultado.append("X");
            }
            StringBuilder temporal = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                String caracter = credito.substring(tamanio - 1, tamanio);
                temporal = new StringBuilder(caracter.concat(temporal.toString()));
                tamanio--;
            }
            resultado = new StringBuilder(resultado.substring(0, tamanio));
            return resultado.append(temporal).toString();
        }else {
            return credito;
        }
    }

    public static CommonAspectDto getCommon(Object[] args) {
        String credito = "";
        String identificacion = "";
        if (args[2] instanceof CertificadoPayload) {
            CertificadoPayload data = (CertificadoPayload) args[2];
            identificacion = data.getIdentificacion();
            credito = data.getNumeroCredito();

        }
        return CommonAspectDto.builder()
                .token((String) args[0])
                .servicioEnum((ServiciosEnum) args[1])
                .numPeticionServicio((Long) args[4])
                .credito(SecurityUtilities.desencriptarCatch(credito))
                .identificacion(identificacion)
                .build();
    }

}

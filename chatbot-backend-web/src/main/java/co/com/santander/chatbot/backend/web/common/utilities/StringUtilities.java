package co.com.santander.chatbot.backend.web.common.utilities;

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
        StringBuilder resultado = new StringBuilder();
        int tamanio = credito.length();
        for (int i = 0; i < tamanio; i++) {
            resultado.append("X");
        }
        StringBuilder temporal = new StringBuilder();
        for(int i = 0 ; i < 5 ; i++){
            String caracter = credito.substring(tamanio-1, tamanio);
            temporal = new StringBuilder(caracter.concat(temporal.toString()));
            tamanio--;
        }
        resultado = new StringBuilder(resultado.substring(0, tamanio));
        return resultado.append(temporal).toString();
    }

}

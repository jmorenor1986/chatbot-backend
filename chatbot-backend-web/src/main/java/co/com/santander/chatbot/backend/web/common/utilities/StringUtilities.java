package co.com.santander.chatbot.backend.web.common.utilities;

public class StringUtilities {
    public static String ofuscarString(String texto, int caracteres) {
        String result = "";
        for (int i = 0; i < texto.length(); i++) {
            if (i >= texto.length() - caracteres) {
                break;
            }
            result += "X";
        }
        return result.concat(texto.substring(texto.length() - caracteres));

    }

    public static String ofuscarCorreo(String correo, int caracteres) {
        String[] result = correo.split("@");
        return ofuscarString(result[0], caracteres).concat("@").concat(result[1]);
    }


    public static String ofuscarCredito(String credito) {
        String resultado = "";
        int tamanio = credito.length();
        for (int i = 0; i < tamanio; i++) {
            resultado += "x";
        }
        String temporal = "";
        for(int i = 0 ; i < 5 ; i++){
            String caracter = credito.substring(tamanio-1, tamanio);
            temporal =   caracter + temporal;
            tamanio--;
        }
        resultado = resultado.substring(0, tamanio);
        return resultado + temporal;
    }

}

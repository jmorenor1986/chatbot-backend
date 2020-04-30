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

}

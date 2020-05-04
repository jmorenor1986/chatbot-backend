package co.com.santander.chatbot.domain.enums;

public enum ServiciosEnum {

    SERVICIO_PAZ_Y_SALVO("Paz y Salvo"),
    SERVICIO_VALIDA_CLIENTE("Valida cliente"),
    SERVICIO_ENLACE_PSE("Busca enlace PSE"),
    SERVICIO_DEBITO_AUTOMATICO("Debito Automático"),
    SERVICIO_INFORMACION_CREDITO("Informacion  credito"),
    SERVICIO_OBTENER_CREDITOS("Obtener creditos"),
    SERVICIO_DECLARACION_RENTA("Declaracion Renta");

    private final String message;

    ServiciosEnum(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }

    public static ServiciosEnum findEnum(String valor) {
        switch (valor) {
            case "Paz y Salvo":
                return SERVICIO_PAZ_Y_SALVO;
            case "Valida cliente":
                return SERVICIO_VALIDA_CLIENTE;
            case "Busca enlace PSE":
                return SERVICIO_ENLACE_PSE;
            case "Debito Automático":
                return SERVICIO_DEBITO_AUTOMATICO;
            case "Informacion  credito":
                return SERVICIO_INFORMACION_CREDITO;
            case "Obtener creditos":
                return SERVICIO_OBTENER_CREDITOS;
            case "Declaracion Renta":
                return SERVICIO_DECLARACION_RENTA;
            default:
                return null;
        }
    }

}

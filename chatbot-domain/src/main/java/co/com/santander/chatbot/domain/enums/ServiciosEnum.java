package co.com.santander.chatbot.domain.enums;

public enum ServiciosEnum {

    SERVICIO_PAZ_Y_SALVO("Paz y Salvo"),
    SERVICIO_VALIDA_CLIENTE("Valida cliente"),
    SERVICIO_ENLACE_PSE("Busca enlace PSE");

    private final String message;

    ServiciosEnum(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }

}

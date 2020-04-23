package co.com.santander.chatbot.domain.enums;

public enum ServiciosEnum {
    SERVICIO_PAZ_Y_SALVO("Paz y Salvo");

    private final String message;

    ServiciosEnum(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }

}

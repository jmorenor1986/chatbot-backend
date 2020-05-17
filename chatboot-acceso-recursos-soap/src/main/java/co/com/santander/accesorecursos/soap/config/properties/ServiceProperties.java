package co.com.santander.accesorecursos.soap.config.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "services")
public class ServiceProperties {
    private String usuarioRemoto;
    private String cliente;
    private String producto;
    private String user;
    private String password;
    private String documentos;
    private String getToken;

}

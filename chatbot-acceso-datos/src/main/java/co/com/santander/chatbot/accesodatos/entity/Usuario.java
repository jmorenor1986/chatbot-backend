package co.com.santander.chatbot.accesodatos.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class UsuarioEntity {
    @Id
    private Long id;
    private Integer telefono;
    private String colaIdentificacion;

}

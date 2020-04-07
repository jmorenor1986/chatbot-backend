package co.com.santander.chatbot.accesodatos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Usuario.consultarUsuarioXTelefonoId", query = "FROM Usuario u where u.telefono = ?1 and u.colaIdentificacion = ?2 ")
public class Usuario {
    @Id
    private Long id;
    private Long telefono;
    @Column(name = "cola_identificacion")
    private String colaIdentificacion;

}

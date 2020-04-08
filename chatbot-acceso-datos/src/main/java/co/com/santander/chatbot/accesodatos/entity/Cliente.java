package co.com.santander.chatbot.accesodatos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.math.BigInteger;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Cliente.consultarUsuarioXTelefonoId", query = "FROM Cliente u where u.telefono = ?1 and u.colaIdentificacion = ?2 ")
public class Cliente {
    @Id
    private Long id;
    @Column(precision = 20, scale = 2)
    private BigInteger telefono;
    @Column(name = "cola_identificacion")
    private String colaIdentificacion;

}

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
@NamedQuery(name = "Cliente.consultarClienteXTelefonoId", query = "FROM Cliente u where u.celular = ?1 and u.numeroIdentificacion like ?2 ")
public class Cliente {
    @Id
    private Long id;
    @Column(name = "Celular", precision = 20, scale = 2)
    private BigInteger celular;
    @Column(name = "NumeroIdentificacion", precision = 20, scale = 2)
    private BigInteger numeroIdentificacion;

}

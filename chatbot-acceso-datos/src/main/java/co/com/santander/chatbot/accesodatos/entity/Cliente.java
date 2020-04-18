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
@NamedQuery(name = "Cliente.consultarXCedulaYTelefono", query = "FROM Cliente u where u.telefono = ?1 and u.cedula like ?2 ")
public class Cliente {
    @Id
    private Long id;
    @Column(name = "Nombre_Cliente")
    private String nombreCliente;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Cedula")
    private String cedula;
    @Column(name = "Email")
    private String email;
    @Column(name = "Numero_Credito")
    private String numerCredito;
    @Column(name = "Banco")
    private String banco;
    @Column(name = "Estado")
    private String estado;
    @Column(name = "id_Producto")
    private String idProducto;
    @Column(name = "id_Banco")
    private String idBanco;
    @Column(name = "convenio")
    private String convenio;

}

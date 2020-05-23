package co.com.santander.chatbot.accesodatos.entity;

import co.com.santander.chatbot.domain.enums.TipoCredito;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "consulta_Cliente")
@NamedQuery(name = "Cliente.consultarXCedulaYTelefono", query = "FROM Cliente u where u.telefono = ?1 and u.cedula like ?2 ")
public class Cliente {

    //private Long id;
    @Column(name = "nombrecliente")
    private String nombreCliente;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Cedula")
    private String cedula;
    @Column(name = "Email")
    private String email;
    @Id
    @Column(name = "numerocredito")
    private String numerCredito;
    @Column(name = "Banco")
    private String banco;
    @Column(name = "Estado")
    private String estado;
    @Column(name = "idproducto")
    private String idProducto;
    @Column(name = "idbanco")
    private String idBanco;
    @Column(name = "convenio")
    private String convenio;
    @Column(name = "valorapagar")
    private Long valorPagar;
    @Column( name = "valormora")
    private Long valorMora;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "tipoproducto")
    private TipoCredito tipoCredito;



}

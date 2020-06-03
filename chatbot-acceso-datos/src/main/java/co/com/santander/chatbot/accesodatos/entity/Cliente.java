package co.com.santander.chatbot.accesodatos.entity;

import co.com.santander.chatbot.domain.enums.TipoCredito;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "consulta_Cliente")
@NamedQuery(name = "Cliente.consultarXCedulaYTelefono", query = "FROM Cliente u where u.telefono = ?1 and u.cedula like ?2 ")
public class Cliente {


    @Column(name = "nombrecliente")
    private String nombreCliente;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "cedula")
    private String cedula;
    @Column(name = "email")
    private String email;
    @Id
    @Column(name = "numerocredito")
    private String numerCredito;
    @Column(name = "banco")
    private String banco;
    @Column(name = "estado")
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
    @Temporal(TemporalType.DATE)
    @Column( name = "fechadesembolso")
    private Date fechaDesembolso;
    @Column( name = "saldocapital")
    private BigDecimal saldoCapital;
    @Column( name = "valordesembolso")
    private BigDecimal valorDesembolso;

}

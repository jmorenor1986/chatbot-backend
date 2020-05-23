package co.com.santander.chatbot.accesodatos.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "infowhatsappwS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "InfoWhatsAppWS.findByNumCreditoBancoAndNumeroIdentificacionAndNumPeticionServicio", query = "FROM InfoWhatsAppWS u where u.numCreditoBanco = ?1 and u.numeroIdentificacion = ?2 and u.numPeticionServicio = ?3 order by id desc ")
public class InfoWhatsAppWS {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numcreditobanco")
    private String numCreditoBanco;

    @Column(name = "numeroidentificacion")
    private String numeroIdentificacion;

    @Column(name = "numpeticionservicio")
    private Long numPeticionServicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaenvio")
    private Date fechaEnvio;

    @Column(name = "estado")
    private Long estado;
}

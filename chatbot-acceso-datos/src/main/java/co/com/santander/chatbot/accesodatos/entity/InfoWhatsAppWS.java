package co.com.santander.chatbot.accesodatos.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "info_whats_appws")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "InfoWhatsAppWS.findByNumCreditoBancoAndNumeroIdentificacionAndNumPeticionServicio", query = "FROM InfoWhatsAppWS u where u.numCreditoBanco = ?1 and u.numeroIdentificacion = ?2 and u.numPeticionServicio = ?3 order by id desc ")
public class InfoWhatsAppWS {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "infowhatsappws_generator")
    @SequenceGenerator(name = "infowhatsappws_generator", sequenceName = "infowhatsappws_seq", allocationSize = 1)
    private Long id;

    @Column(name = "num_creditoBanco")
    private String numCreditoBanco;

    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @Column(name = "num_peticion_servicio")
    private Long numPeticionServicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_envio")
    private Date fechaEnvio;
    @Column(name = "estado")
    private Long estado;
}

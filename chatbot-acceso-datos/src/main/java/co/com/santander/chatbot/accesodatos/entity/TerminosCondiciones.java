package co.com.santander.chatbot.accesodatos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "terminos_condiciones")
public class TerminosCondiciones {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "terminos_condiciones_generator")
    @SequenceGenerator(name = "terminos_condiciones_generator", sequenceName = "terminos_condiciones_seq", allocationSize = 1)
    private Long id;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name = "hora_enviado_term")
    private Date horaEnviadoTeminos;
    @Column(name = "hora_operacion")
    private Date horaOperacion;
    @Column(name = "operacion")
    private Long operacion;
}

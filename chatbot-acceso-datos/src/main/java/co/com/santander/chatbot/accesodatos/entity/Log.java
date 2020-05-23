package co.com.santander.chatbot.accesodatos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "log_cliente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_generator")
    @SequenceGenerator(name = "log_generator", sequenceName = "log_seq", allocationSize = 1)
    private Long id;
    @Column(name = "telefono")
    private String telefono;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    private Date feha;
    @Column(name = "response")
    private String traza;
    @Column(name = "request")
    private String request;
    @Column(name = "tipo_operacion")
    private Long tipoOperacion;
    @ManyToOne(fetch = FetchType.LAZY)
    private Servicio servicio;
    @ManyToOne(fetch = FetchType.LAZY)
    private Canal canal;
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "credito")
    private String credito;
    @Column(name = "correo")
    private String correo;

    public void preInsert(){
        if(Objects.isNull(feha)){
            setFeha(new Date());
        }
    }
}

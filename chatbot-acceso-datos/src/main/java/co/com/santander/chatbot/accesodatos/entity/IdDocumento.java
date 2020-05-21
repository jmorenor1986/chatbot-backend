package co.com.santander.chatbot.accesodatos.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "id_documentos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdDocumento {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_documentos_generator")
    @SequenceGenerator(name = "id_documentos_generator", sequenceName = "id_documentos_seq", allocationSize = 1)
    private Long id;
    @Column(name = "id_documentos")
    private String idDocumentos;
    @Column(name = "anio")
    private String anio;
    @Column(name = "mes")
    private String mes;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    private Date fecha;

    @PrePersist
    private void prePersistFunction(){
        if(Objects.isNull(fecha)){
            setFecha(new Date());
        }
    }
}
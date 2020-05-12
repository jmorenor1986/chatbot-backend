package co.com.santander.chatbot.accesodatos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servcios")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicio_generator")
    @SequenceGenerator(name = "servicio_generator", sequenceName = "servicio_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nombre_servicio")
    private String nombre;

    @OneToMany(
            mappedBy = "servicio",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Log> logs = new ArrayList<>();

    @OneToMany(
            mappedBy = "servicio",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ParametrosServicio> parametros = new ArrayList<>();
}

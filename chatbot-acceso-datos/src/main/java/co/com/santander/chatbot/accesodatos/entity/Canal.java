package co.com.santander.chatbot.accesodatos.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "canal")
@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class Canal {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "canal_generator")
    @SequenceGenerator(name = "canal_generator", sequenceName = "canal_seq", allocationSize = 1)
    private Long id;
    @Column(name = "nombre_canal")
    private String nombre;
    @OneToMany(
            mappedBy = "canal",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Log> logs = new ArrayList<>();

    @OneToMany(
            mappedBy = "canal",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ParametrosServicio> parametros = new ArrayList<>();
}

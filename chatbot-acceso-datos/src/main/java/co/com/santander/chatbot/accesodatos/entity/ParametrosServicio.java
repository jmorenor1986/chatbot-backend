package co.com.santander.chatbot.accesodatos.entity;

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
@NamedQuery(name = "ParametrosServicio.findByNameService", query = "FROM ParametrosServicio param inner join param.servicio ser  where ser.nombre = ?1")
public class ParametrosServicio {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Servicio servicio;
    @ManyToOne(fetch = FetchType.LAZY)
    private Canal canal;
    private Integer numeroIntentos;
    private Integer tiempoIntentos;
}

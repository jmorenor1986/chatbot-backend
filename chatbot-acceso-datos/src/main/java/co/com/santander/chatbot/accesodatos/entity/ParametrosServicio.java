package co.com.santander.chatbot.accesodatos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "ParametrosServicio.findByNameService", query = "FROM ParametrosServicio u where u.servicio = ?1")

public class ParametrosServicio {
    @Id
    private Long id;
    private String canal;
    private String servicio;
    private Integer numeroIntentos;
    private Integer tiempoIntentos;
}

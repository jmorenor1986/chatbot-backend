package co.com.santander.chatbot.accesodatos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "parametros_app")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParametrosApp {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parametros_app_generator")
    @SequenceGenerator(name = "parametros_app_generator", sequenceName = "parametros_app_seq", allocationSize = 1)
    private Long id;
    @Column(name = "clave")
    private String clave;
    @Column(name = "valor")
    private String valor;
}

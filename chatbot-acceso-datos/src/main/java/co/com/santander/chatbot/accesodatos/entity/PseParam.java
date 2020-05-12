package co.com.santander.chatbot.accesodatos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pse_param")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PseParam {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pse_param_generator")
    @SequenceGenerator(name = "pse_param_generator", sequenceName = "pse_param_seq", allocationSize = 1)
    private Long id;
    @Column(name = "id_banco")
    private Long idBanco;
    @Column(name = "tipo_credito")
    private Long tipoCredito;
    @Column(name = "url")
    private String url;

}

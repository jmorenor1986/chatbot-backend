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
@Table(name = "usuario_app")
public class UsuarioApp {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_app_generator")
    @SequenceGenerator(name = "usuario_app_generator", sequenceName = "usuario_app_seq", allocationSize = 1)
    private Long id;
    @Column(name = "usuario", nullable = false, unique = true)
    private String usuario;
    @Column(name = "password" )
    private String contra;
}

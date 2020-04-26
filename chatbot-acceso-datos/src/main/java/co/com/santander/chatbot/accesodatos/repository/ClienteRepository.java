package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente consultarXCedulaYTelefono(String telefono, String cedula);

    List<Cliente> findByTelefono(String telefono);

    List<Cliente> findByTelefonoAndNumerCredito(String telefono, String numCredito);

    List<Cliente> findByCedulaEndingWithAndNumerCredito(String cedula, String numeroCredito);

}
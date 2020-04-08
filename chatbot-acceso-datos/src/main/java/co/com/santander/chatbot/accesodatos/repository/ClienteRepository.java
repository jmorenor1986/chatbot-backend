package co.com.santander.chatbot.accesodatos.repository;

import co.com.santander.chatbot.accesodatos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente consultarClienteXTelefonoId(BigInteger telefono, String colaIdentificacion);
}

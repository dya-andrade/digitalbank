package br.com.digitalbank.conta.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digitalbank.conta.models.conta.Corrente;

@Repository
public interface CorrenteRepository extends JpaRepository<Corrente, Long> {

	Optional<Corrente> findByCpfCliente(String cpfCliente);

}

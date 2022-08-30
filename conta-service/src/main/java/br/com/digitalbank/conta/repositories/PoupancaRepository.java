package br.com.digitalbank.conta.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.digitalbank.conta.models.conta.Poupanca;

@Repository
public interface PoupancaRepository extends JpaRepository<Poupanca, Long> {

	Optional<Poupanca> findByCpfCliente(@Param("cpf") String cpf);

}

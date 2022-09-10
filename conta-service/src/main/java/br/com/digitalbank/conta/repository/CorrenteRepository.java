package br.com.digitalbank.conta.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.digitalbank.conta.model.conta.ContaCompleta;
import br.com.digitalbank.conta.model.conta.Corrente;

@Repository
public interface CorrenteRepository extends JpaRepository<Corrente, Long> {

	Optional<Corrente> findByCpfCliente(@Param("cpf") String cpfCliente);
	
	@Query("SELECT NEW br.com.digitalbank.conta.model.conta.ContaCompleta(c, p) FROM "
			+ "Corrente c INNER JOIN Poupanca p ON c.cpfCliente = p.cpfCliente")
	Page<ContaCompleta> findAllContas(Pageable pageable);
	
}

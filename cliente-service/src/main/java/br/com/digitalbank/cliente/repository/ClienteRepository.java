package br.com.digitalbank.cliente.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.digitalbank.cliente.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByCpf(@Param("cpf") String cpf);

	@Query("SELECT c FROM Cliente c WHERE c.nomeCompleto LIKE LOWER(CONCAT ('%',:nome,'%'))") 
	Page<Cliente> findAllByNome(@Param("nome") String nome, Pageable pageable); 

	@Modifying 
	@Query("UPDATE Cliente c SET c.ativado = false WHERE c.id = :id") 
	void disableCliente(@Param("id") Long id);
}

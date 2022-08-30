package br.com.digitalbank.conta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digitalbank.conta.models.conta.Poupanca;

@Repository
public interface PoupancaRepository extends JpaRepository<Poupanca, Long> {

}

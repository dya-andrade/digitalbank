package br.com.digitalbank.transacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digitalbank.transacao.model.movimentacao.Deposito;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {

}

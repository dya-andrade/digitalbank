package br.com.digitalbank.conta.model.movimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface MovimentacaoNaConta {

	abstract void movimentaNovoValor(BigDecimal novoValor);

	abstract void adicionaMovimentacao(Movimentacao movimentacao);

	abstract LocalDate dataUltimaMovimentacao();
}

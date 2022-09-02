package br.com.digitalbank.conta.service.movimentacao.acoes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.model.conta.Conta;
import br.com.digitalbank.conta.model.movimentacao.Movimentacao;
import br.com.digitalbank.conta.service.movimentacao.validacoes.ValidacaoMovimentacao;

@Component
public class RealizaMovimentacaoNaConta {

	@Autowired
	private List<ValidacaoMovimentacao> validacoes;
	
	
	public void executaNovaMovimentacao(Conta conta, Movimentacao movimentacao) {
		this.validacoes.forEach(v -> v.valida(conta, movimentacao));
		
		conta.adicionaMovimentacao(movimentacao);
		conta.movimentaNovoValor(calculaNovoValor(conta, movimentacao));
	}

	public BigDecimal calculaNovoValor(Conta conta, Movimentacao movimentacao) {
		BigDecimal percentual = movimentacao.getPercentual().divide(new BigDecimal(100));
		BigDecimal novoValor = conta.getValor().multiply(percentual).setScale(2, RoundingMode.HALF_EVEN);

		return novoValor;
	}
}

package br.com.digitalbank.conta.services.acoes;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.models.conta.Conta;
import br.com.digitalbank.conta.models.movimentacao.Movimentacao;
import br.com.digitalbank.conta.services.validacoes.ValidacaoMovimentacao;
import br.com.digitalbank.conta.services.validacoes.ValidacaoPercentualMovimentacao;
import br.com.digitalbank.conta.services.validacoes.ValidacaoPeriodicidadeEntreMovimentacoes;

@Component
public class RealizaMovimentacao {

	private List<ValidacaoMovimentacao> validacoes;
	
	
	public RealizaMovimentacao(List<ValidacaoMovimentacao> validacoes) {
		this.validacoes = validacoes;
		validacoes.add(new ValidacaoPercentualMovimentacao());
		validacoes.add(new ValidacaoPeriodicidadeEntreMovimentacoes());
	}
	
	public void executa(Conta conta, Movimentacao movimentacao) {
		this.validacoes.forEach(v -> v.valida(conta, movimentacao));
		
		conta.adicionaMovimentacao(movimentacao);
		conta.movimentaNovoValor(movimentacao.getValor());
	}
}

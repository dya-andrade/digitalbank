package br.com.digitalbank.conta.services.acoes;

import br.com.digitalbank.conta.models.conta.Conta;
import br.com.digitalbank.conta.models.movimentacao.Movimentacao;

public class RealizaMovimentacao implements ExecutaMovimentacao {

	@Override
	public void executa(Conta conta, Movimentacao movimentacao) {
		conta.adicionaMovimentacao(movimentacao);
		conta.movimentaNovoValor(movimentacao.getValor());
	}

}

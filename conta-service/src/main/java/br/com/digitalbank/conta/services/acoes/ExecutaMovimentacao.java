package br.com.digitalbank.conta.services.acoes;

import br.com.digitalbank.conta.models.conta.Conta;
import br.com.digitalbank.conta.models.movimentacao.Movimentacao;

public interface ExecutaMovimentacao {
	
	public void executa(Conta conta, Movimentacao movimentacao);

}

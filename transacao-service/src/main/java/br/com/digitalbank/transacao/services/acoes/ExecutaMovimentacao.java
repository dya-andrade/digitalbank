package br.com.digitalbank.transacao.services.acoes;

import br.com.digitalbank.transacao.models.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.models.transacao.Transacao;

public interface ExecutaMovimentacao {
	
	public void executa(Movimentacao movimentacao, Transacao transacao);

}

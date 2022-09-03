package br.com.digitalbank.transacao.service.acao;

import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;

public interface RealizaTransacao {
	
	void executa(Movimentacao movimentacao, Transacao transacao);

}

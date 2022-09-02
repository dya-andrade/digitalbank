package br.com.digitalbank.transacao.service.acoes;

import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;

public interface AcaoTransacao {
	
	void executa(Movimentacao movimentacao, Transacao transacao);

}

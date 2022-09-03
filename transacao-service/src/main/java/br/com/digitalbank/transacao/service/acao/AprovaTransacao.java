package br.com.digitalbank.transacao.service.acao;

import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;

public class AprovaTransacao implements RealizaTransacao {

	@Override
	public void executa(Movimentacao movimentacao, Transacao transacao) {
		transacao.aplicaTarifaExtra();
		transacao.aprova();		
		
		movimentacao.adicionaTransacao(transacao);
	}
}

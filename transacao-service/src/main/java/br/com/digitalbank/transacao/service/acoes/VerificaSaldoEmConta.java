package br.com.digitalbank.transacao.service.acoes;

import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;

public class VerificaSaldoEmConta implements AcaoTransacao {

	@Override
	public void executa(Movimentacao movimentacao, Transacao transacao) {
		transacao.aplicaTarifaExtra();
		transacao.aprova();		
		
		movimentacao.adicionaTransacao(transacao);
	}
}

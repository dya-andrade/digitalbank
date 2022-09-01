package br.com.digitalbank.transacao.services.acoes;

import br.com.digitalbank.transacao.models.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.models.transacao.Transacao;

public class VerificaSaldoEmConta implements AcaoTransacao {

	@Override
	public void executa(Movimentacao movimentacao, Transacao transacao) {
		transacao.aplicaTarifaExtra();
		transacao.aprova();		
		
		movimentacao.adicionaTransacao(transacao);
	}
}

package br.com.digitalbank.transacao.services.acoes;

import br.com.digitalbank.transacao.models.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.models.transacao.Transacao;

public class VerificaSaldoEmContas implements ExecutaMovimentacao {

	@Override
	public void executa(Movimentacao movimentacao, Transacao transacao) {

		movimentacao.getCpfConta();
		movimentacao.getCpfTransacao();
		//verifica saldo em contas
		
		transacao.aplicaTarifaExtra();
		transacao.aprova();
		//aprova transacao
		
		
		movimentacao.adicionaTransacao(transacao);
		
	}

}

package br.com.digitalbank.transacao.service.acao;

import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;
import br.com.digitalbank.transacao.model.transacao.TransacaoCompleta;

public class CompletaTransacao implements FinalizaTransacao {

	@Override
	public TransacaoCompleta finaliza(Movimentacao movimentacao, Transacao transacao) {

		return new TransacaoCompleta(movimentacao.getCpfConta(),
				movimentacao.getCpfTransacao(), transacao.getValor());
	}

}

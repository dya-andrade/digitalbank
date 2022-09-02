package br.com.digitalbank.transacao.model.transacao.situacao;

import br.com.digitalbank.transacao.model.transacao.Transacao;

public class Reprovado extends SituacaoTransacao {

	@Override
	public void finaliza(Transacao transacao) {
		transacao.setSituacao(new Finalizado());
	}
}

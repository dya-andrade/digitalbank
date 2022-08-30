package br.com.digitalbank.transacao.models.transacao.situacao;

import br.com.digitalbank.transacao.models.transacao.Transacao;

public class Reprovado extends SituacaoTransacao {

	@Override
	public void finaliza(Transacao transacao) {
		transacao.setSituacao(new Finalizado());
	}
}

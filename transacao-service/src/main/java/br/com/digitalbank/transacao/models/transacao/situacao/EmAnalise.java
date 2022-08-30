package br.com.digitalbank.transacao.models.transacao.situacao;

import br.com.digitalbank.transacao.models.transacao.Transacao;

public class EmAnalise extends SituacaoTransacao {
	
	@Override
	public void aprova(Transacao transacao) {
		transacao.setSituacao(new Aprovado());
	}
	
	@Override
	public void reprova(Transacao transacao) {
		transacao.setSituacao(new Reprovado());
	}
}

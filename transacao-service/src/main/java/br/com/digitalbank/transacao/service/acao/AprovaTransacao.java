package br.com.digitalbank.transacao.service.acao;

import org.springframework.stereotype.Component;

import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;

@Component
public class AprovaTransacao implements RealizaTransacao {

	@Override
	public void executa(Movimentacao movimentacao, Transacao transacao) {
		transacao.aplicaTarifaExtra();
		
		movimentacao.adicionaTransacao(transacao);
	}
}

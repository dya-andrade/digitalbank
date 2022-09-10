package br.com.digitalbank.transacao.service.acao;

import org.springframework.stereotype.Component;

import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;
import br.com.digitalbank.transacao.model.transacao.TransacaoCompleta;

@Component
public class CompletaTransacao implements FinalizaTransacao {

	@Override
	public TransacaoCompleta finaliza(Movimentacao movimentacao, Transacao transacao) {

		transacao.finaliza();
		
		return new TransacaoCompleta(movimentacao.getCpfContaOrigem(),
				movimentacao.getCpfContaDestino(), transacao.getValor());
	}

}

package br.com.digitalbank.transacao.service.acao;

import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;
import br.com.digitalbank.transacao.model.transacao.TransacaoCompleta;

public interface FinalizaTransacao {
	
	TransacaoCompleta finaliza(Movimentacao movimentacao, Transacao transacao);
}

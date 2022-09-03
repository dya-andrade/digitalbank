package br.com.digitalbank.transacao.service.acao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;
import br.com.digitalbank.transacao.proxy.ContaProxy;
import br.com.digitalbank.transacao.response.ContaCompleta;

@Component
public class VerificaSaldoEmConta implements RealizaTransacao {

	@Autowired
	private ContaProxy proxy;
	
	@Override
	public void executa(Movimentacao movimentacao, Transacao transacao) {
		
		ContaCompleta contaOrigem = proxy.buscaContaPorCpf(movimentacao.getCpfConta());
		
		Integer valorOrigm = transacao.getValor().compareTo(contaOrigem.getCorrente().getValor());
		
		if(valorOrigm < 0) 
			transacao.reprova();
		
	}
}

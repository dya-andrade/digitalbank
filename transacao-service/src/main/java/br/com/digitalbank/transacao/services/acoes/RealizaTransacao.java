package br.com.digitalbank.transacao.services.acoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.transacao.models.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.models.transacao.Transacao;
import br.com.digitalbank.transacao.proxy.ContaProxy;
import br.com.digitalbank.transacao.response.ContaCompleta;

@Component
public class RealizaTransacao implements AcaoTransacao {

	@Autowired
	private ContaProxy proxy;
	
	@Override
	public void executa(Movimentacao movimentacao, Transacao transacao) {
		
		ContaCompleta contaOrigem = proxy.findByCpf(movimentacao.getCpfConta());
		
		Integer valorOrigm = transacao.getValor().compareTo(contaOrigem.getCorrente().getValor());
		
		if(valorOrigm < 0) 
			transacao.reprova();
		
	}
}

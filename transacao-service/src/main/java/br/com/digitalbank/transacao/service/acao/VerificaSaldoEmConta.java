package br.com.digitalbank.transacao.service.acao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;
import br.com.digitalbank.transacao.proxy.ContaProxy;
import br.com.digitalbank.transacao.response.ContaCompleta;

@Component
public class VerificaSaldoEmConta implements ExecutaTransacao {

	@Autowired
	private ContaProxy proxy;
	
	@Override
	public void executa(Movimentacao movimentacao, Transacao transacao) {
		
		ContaCompleta contaOrigem = proxy.buscaContaPorCpf(movimentacao.getCpfContaOrigem());
		
		Integer verificaSaldo = contaOrigem.getCorrente().getValor().compareTo(transacao.getValor());
		
		if(verificaSaldo < 0) 
			transacao.reprova();
		
		transacao.aprova();		
	}
}

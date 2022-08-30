package br.com.digitalbank.transacao.services;

import java.util.List;

import br.com.digitalbank.transacao.models.movimentacao.Deposito;
import br.com.digitalbank.transacao.models.movimentacao.Transferencia;
import br.com.digitalbank.transacao.models.transacao.Transacao;
import br.com.digitalbank.transacao.services.acoes.ExecutaMovimentacao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MovimentacaoService {
	
	private final List<ExecutaMovimentacao> acoes;

	public void executaMovimentacaoDeposito(Deposito deposito, Transacao transacao) {
		this.acoes.forEach(a -> a.executa(deposito, transacao));
	}
	
	public void executaMovimentacaoTransferencia(Transferencia transferencia, Transacao transacao) {
		this.acoes.forEach(a -> a.executa(transferencia, transacao));
	}
}

package br.com.digitalbank.conta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digitalbank.conta.models.conta.Conta;
import br.com.digitalbank.conta.models.movimentacao.Movimentacao;
import br.com.digitalbank.conta.services.acoes.ExecutaMovimentacao;
import br.com.digitalbank.conta.services.validacoes.ValidacaoMovimentacao;

@Service
public class MovimentacaoService<M extends Movimentacao, C extends Conta> {

	@Autowired
	private List<ValidacaoMovimentacao> validacoes;

	@Autowired
	private List<ExecutaMovimentacao> acoes;

	public void aplicaMovimentacao(C conta, M movimentacao) {

		this.validacoes.forEach(v -> v.valida(conta, movimentacao));

		this.acoes.forEach(a -> a.executa(conta, movimentacao));
	}
}

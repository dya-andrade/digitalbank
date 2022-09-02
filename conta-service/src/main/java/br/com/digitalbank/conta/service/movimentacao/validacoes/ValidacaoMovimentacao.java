package br.com.digitalbank.conta.service.movimentacao.validacoes;

import br.com.digitalbank.conta.model.conta.Conta;
import br.com.digitalbank.conta.model.movimentacao.Movimentacao;

public interface ValidacaoMovimentacao {

    void valida(Conta conta, Movimentacao movimentacao);
}

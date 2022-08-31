package br.com.digitalbank.conta.services.movimentacao.validacoes;

import br.com.digitalbank.conta.models.conta.Conta;
import br.com.digitalbank.conta.models.movimentacao.Movimentacao;

public interface ValidacaoMovimentacao {

    void valida(Conta conta, Movimentacao movimentacao);
}

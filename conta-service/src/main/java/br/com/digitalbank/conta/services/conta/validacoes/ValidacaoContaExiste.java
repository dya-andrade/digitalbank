package br.com.digitalbank.conta.services.conta.validacoes;

import br.com.digitalbank.conta.models.conta.ContaCompleta;

public interface ValidacaoContaExiste {

	ContaCompleta valida(String cpf);
}

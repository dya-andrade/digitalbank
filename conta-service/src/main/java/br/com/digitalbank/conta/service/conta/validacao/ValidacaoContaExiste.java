package br.com.digitalbank.conta.service.conta.validacao;

import br.com.digitalbank.conta.model.conta.ContaCompleta;

public interface ValidacaoContaExiste {

	ContaCompleta valida(String cpf);
}

package br.com.digitalbank.cliente.service.validacoes;

import br.com.digitalbank.cliente.model.Cliente;

public interface ValidacaoClienteExiste {

	Cliente valida(String cpf);
}

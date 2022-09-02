package br.com.digitalbank.cliente.service.validacoes;

import br.com.digitalbank.cliente.models.Cliente;

public interface ValidacaoClienteExiste {

	Cliente valida(String cpf);
}

package br.com.digitalbank.cliente.service.validacao;

import br.com.digitalbank.cliente.model.Cliente;

public interface ValidacaoClienteExiste {

	Cliente valida(String cpf);
}

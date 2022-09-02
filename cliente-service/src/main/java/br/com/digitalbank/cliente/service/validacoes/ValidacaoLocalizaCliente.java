package br.com.digitalbank.cliente.service.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.cliente.exception.ResourceNotFoundException;
import br.com.digitalbank.cliente.model.Cliente;
import br.com.digitalbank.cliente.repository.ClienteRepository;

@Component
public class ValidacaoLocalizaCliente implements ValidacaoClienteExiste {

	@Autowired
	private ClienteRepository repository;

	@Override
	public Cliente valida(String cpf) {
		var entity = repository.findByCpf(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma cliente encontrado com este CPF!"));
		return entity;
	}

}

package br.com.digitalbank.cliente.service.validacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.cliente.data.vo.v1.ClienteVO1;
import br.com.digitalbank.cliente.exception.EntityPersistenceException;
import br.com.digitalbank.cliente.model.Cliente;
import br.com.digitalbank.cliente.repository.ClienteRepository;

@Component
public class ValidacaoClienteDuplicado implements ValidacaoClienteNovo {
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public void valida(ClienteVO1 cliente) {

		Optional<Cliente> clienteDuplicado = repository.findByCpf(cliente.getCpf());

		if (clienteDuplicado.isPresent())
			throw new EntityPersistenceException("Já existe um cliente com este CPF.");
	}

}

package br.com.digitalbank.cliente.service.validacoes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.cliente.data.vo.v1.ClienteVO;
import br.com.digitalbank.cliente.exceptions.EntityPersistenceException;
import br.com.digitalbank.cliente.models.Cliente;
import br.com.digitalbank.cliente.repository.ClienteRepository;

@Component
public class ValidacaoClienteDuplicado implements ValidacaoClienteNovo {
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public void valida(ClienteVO cliente) {

		Optional<Cliente> clienteDuplicado = repository.findByCpf(cliente.getCpf());

		if (clienteDuplicado.isPresent())
			throw new EntityPersistenceException("Já existe um cliente com este CPF.");
	}

}

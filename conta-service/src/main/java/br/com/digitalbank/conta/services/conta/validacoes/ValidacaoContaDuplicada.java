package br.com.digitalbank.conta.services.conta.validacoes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.exceptions.EntityPersistenceException;
import br.com.digitalbank.conta.models.conta.Corrente;
import br.com.digitalbank.conta.repositories.CorrenteRepository;

@Component
public class ValidacaoContaDuplicada implements ValidacaoCpf {

	@Autowired
	private CorrenteRepository correnteRepository;
	
	@Override
	public void valida(String cpf) {
		Optional<Corrente> contaDuplicada = correnteRepository.findByCpfCliente(cpf);

		if (contaDuplicada.isPresent())
			throw new EntityPersistenceException("Já existe uma conta com este CPF.");		
	}

}

package br.com.digitalbank.conta.service.conta.validacoes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.exception.EntityPersistenceException;
import br.com.digitalbank.conta.model.conta.Corrente;
import br.com.digitalbank.conta.repository.CorrenteRepository;

@Component
public class ValidacaoContaDuplicada implements ValidacaoContaNova {

	@Autowired
	private CorrenteRepository correnteRepository;
	
	@Override
	public void valida(ContaVO conta) {
		Optional<Corrente> contaDuplicada = correnteRepository.findByCpfCliente(conta.getCpfCliente());

		if (contaDuplicada.isPresent())
			throw new EntityPersistenceException("Já existe uma conta com este CPF.");		
	}

}

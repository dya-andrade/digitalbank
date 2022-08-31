package br.com.digitalbank.conta.services.conta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.exceptions.ResourceNotFoundException;
import br.com.digitalbank.conta.models.conta.Corrente;
import br.com.digitalbank.conta.repositories.CorrenteRepository;

@Component
public class ValidacaoCpf {

	@Autowired
	private CorrenteRepository correnteRepository;
	
	public Corrente valida(String cpf) {
		return correnteRepository.findByCpfCliente(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma conta encontrada com este CPF!"));
	}
}

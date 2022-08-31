package br.com.digitalbank.conta.services.conta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.exceptions.ResourceNotFoundException;
import br.com.digitalbank.conta.models.conta.ContaCompleta;
import br.com.digitalbank.conta.repositories.CorrenteRepository;
import br.com.digitalbank.conta.repositories.PoupancaRepository;

@Component
public class ValidacaoContaExiste {

	@Autowired
	private CorrenteRepository correnteRepository;
	
	@Autowired
	private PoupancaRepository poupancaRepository;

	
	public ContaCompleta valida(String cpf) {
		var corrente = correnteRepository.findByCpfCliente(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma conta encontrada com este CPF!"));
		
		var poupanca = poupancaRepository.findByCpfCliente(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma conta encontrada com este CPF!"));
	
		return new ContaCompleta(corrente, poupanca);
	}
}

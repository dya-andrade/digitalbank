package br.com.digitalbank.conta.service.conta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.exception.ResourceNotFoundException;
import br.com.digitalbank.conta.model.conta.ContaCompleta;
import br.com.digitalbank.conta.repository.CorrenteRepository;
import br.com.digitalbank.conta.repository.PoupancaRepository;

@Component
public class ValidacaoLocalizaConta implements ValidacaoContaExiste {

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

package br.com.digitalbank.conta.services.conta.acoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.exceptions.EntityPersistenceException;
import br.com.digitalbank.conta.exceptions.ResourceNotFoundException;
import br.com.digitalbank.conta.models.conta.Corrente;
import br.com.digitalbank.conta.repositories.CorrenteRepository;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoClienteAtivo;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoContaDuplicada;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoCpf;

@Component
public class RealizaValidacaoConta {

	@Autowired
	private CorrenteRepository correnteRepository;
	
	private List<ValidacaoCpf> validacoes;
	

	public RealizaValidacaoConta(List<ValidacaoCpf> validacoes) {
		this.validacoes = validacoes;
		validacoes.add(new ValidacaoContaDuplicada());
		validacoes.add(new ValidacaoClienteAtivo());
	}

	public void validaCriacaoDeContaNova(ContaVO conta) {
		if (conta == null)
			throw new EntityPersistenceException("A conta não pode ser nula.");

		this.validacoes.forEach(v -> v.valida(conta.getCpfCliente()));
	}
	
   public Corrente validaEBuscaContaExistente(String cpf) {
	   return correnteRepository.findByCpfCliente(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma conta encontrada com este CPF!"));
   }
}

package br.com.digitalbank.conta.services.conta.acoes;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.exceptions.EntityPersistenceException;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoClienteAtivo;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoContaDuplicada;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoCpf;

@Component
public class RealizaValidacaoConta {

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
}

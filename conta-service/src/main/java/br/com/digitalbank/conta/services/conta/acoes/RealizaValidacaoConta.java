package br.com.digitalbank.conta.services.conta.acoes;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.models.conta.Corrente;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoClienteAtivo;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoConta;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoContaDuplicada;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoContaNula;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoCpf;

@Component
public class RealizaValidacaoConta {

	private ValidacaoCpf validacaoCpf;
	
	private List<ValidacaoConta> validacoesConta;
	

	public RealizaValidacaoConta(List<ValidacaoConta> validacoesConta, ValidacaoCpf validacaoCpf) {
		this.validacoesConta = validacoesConta;
		validacoesConta.add(new ValidacaoContaDuplicada());
		validacoesConta.add(new ValidacaoClienteAtivo());
		validacoesConta.add(new ValidacaoContaNula());
		
		this.validacaoCpf = validacaoCpf;
	}

	public void validaCriacaoDeContaNova(ContaVO conta) {
		this.validacoesConta.forEach(v -> v.valida(conta));
	}
	
   public Corrente validaEBuscaContaExistente(String cpf) {
	   return this.validacaoCpf.valida(cpf);
   }
}

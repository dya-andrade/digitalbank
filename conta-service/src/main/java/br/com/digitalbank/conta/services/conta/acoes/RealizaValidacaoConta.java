package br.com.digitalbank.conta.services.conta.acoes;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.models.conta.ContaCompleta;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoClienteAtivo;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoConta;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoContaDuplicada;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoContaNula;
import br.com.digitalbank.conta.services.conta.validacoes.ValidacaoContaExiste;

@Component
public class RealizaValidacaoConta {

	private ValidacaoContaExiste validacaoContaExiste;
	
	private List<ValidacaoConta> validacoesConta;
	

	public RealizaValidacaoConta(List<ValidacaoConta> validacoesConta, ValidacaoContaExiste validacaoContaExiste) {
		this.validacoesConta = validacoesConta;
		validacoesConta.add(new ValidacaoContaDuplicada());
		validacoesConta.add(new ValidacaoClienteAtivo());
		validacoesConta.add(new ValidacaoContaNula());
		
		this.validacaoContaExiste = validacaoContaExiste;
	}

	public void validaCriacaoDeContaNova(ContaVO conta) {
		this.validacoesConta.forEach(v -> v.valida(conta));
	}
	
   public ContaCompleta validaEBuscaContaExistente(String cpf) {
	   return this.validacaoContaExiste.valida(cpf);
   }
}

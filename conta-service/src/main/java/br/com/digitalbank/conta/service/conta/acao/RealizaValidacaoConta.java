package br.com.digitalbank.conta.service.conta.acao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.model.conta.ContaCompleta;
import br.com.digitalbank.conta.service.conta.validacao.ValidacaoContaExiste;
import br.com.digitalbank.conta.service.conta.validacao.ValidacaoContaNova;

@Component
public class RealizaValidacaoConta {

	@Autowired
	private ValidacaoContaExiste validacaoContaExiste;

	@Autowired
	private List<ValidacaoContaNova> validacoesContaNova;
	

	public void validaCriacaoDeContaNova(ContaVO conta) {
		this.validacoesContaNova.forEach(v -> v.valida(conta));
	}

	public ContaCompleta validaEBuscaContaExistente(String cpf) {
		return this.validacaoContaExiste.valida(cpf);
	}
}

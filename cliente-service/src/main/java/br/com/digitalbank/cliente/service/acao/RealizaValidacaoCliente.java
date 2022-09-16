package br.com.digitalbank.cliente.service.acao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.cliente.data.vo.ClienteVO;
import br.com.digitalbank.cliente.model.Cliente;
import br.com.digitalbank.cliente.service.validacao.ValidacaoClienteExiste;
import br.com.digitalbank.cliente.service.validacao.ValidacaoClienteNovo;

@Component
public class RealizaValidacaoCliente {
	
	@Autowired
	private ValidacaoClienteExiste validacaoClienteExiste;

	@Autowired
	private List<ValidacaoClienteNovo> validacoesClienteNovo;
	

	public void validaCriacaoNovoCliente(ClienteVO cliente) {
		this.validacoesClienteNovo.forEach(v -> v.valida(cliente));
	}

	public Cliente validaEBuscaClienteExistente(String cpf) {
		return this.validacaoClienteExiste.valida(cpf);
	}

}

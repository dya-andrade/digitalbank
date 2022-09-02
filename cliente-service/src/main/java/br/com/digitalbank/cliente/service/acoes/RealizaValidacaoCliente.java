package br.com.digitalbank.cliente.service.acoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.cliente.data.vo.v1.ClienteVO;
import br.com.digitalbank.cliente.models.Cliente;
import br.com.digitalbank.cliente.service.validacoes.ValidacaoClienteExiste;
import br.com.digitalbank.cliente.service.validacoes.ValidacaoClienteNovo;

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

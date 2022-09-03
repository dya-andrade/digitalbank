package br.com.digitalbank.conta.service.conta.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.exception.EntityPersistenceException;
import br.com.digitalbank.conta.proxy.ClienteProxy;
import br.com.digitalbank.conta.response.Cliente;

@Component
public class ValidacaoClienteAtivo implements ValidacaoContaNova {

	@Autowired
	private ClienteProxy proxy;
	
	@Override
	public void valida(ContaVO conta) {
		Cliente cliente = proxy.buscaClientePorCpf(conta.getCpfCliente());
		
		if(!cliente.getAtivado())
			throw new EntityPersistenceException("Cliente está desativado no Digital Bank.");
	}
}

package br.com.digitalbank.conta.services.conta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.digitalbank.conta.exceptions.EntityPersistenceException;
import br.com.digitalbank.conta.proxy.ClienteProxy;
import br.com.digitalbank.conta.response.Cliente;

@Component
public class ValidacaoClienteAtivo implements ValidacaoCpf {

	@Autowired
	private ClienteProxy proxy;
	
	@Override
	public void valida(String cpf) {
		Cliente cliente = proxy.findByCpf(cpf);
		
		if(!cliente.getAtivado())
			throw new EntityPersistenceException("Cliente está desativado no Digital Bank.");
	}
}

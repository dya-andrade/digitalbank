package br.com.digitalbank.cliente.service.validacao;

import br.com.digitalbank.cliente.data.vo.v1.ClienteVO1;
import br.com.digitalbank.cliente.exception.EntityPersistenceException;

public class ValidacaoClienteNulo implements ValidacaoClienteNovo {

	@Override
	public void valida(ClienteVO1 cliente) {
		if(cliente == null) 
			throw new EntityPersistenceException("O cliente não pode ser nulo.");		
	}
}

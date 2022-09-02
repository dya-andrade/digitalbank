package br.com.digitalbank.cliente.service.validacoes;

import br.com.digitalbank.cliente.data.vo.v1.ClienteVO;
import br.com.digitalbank.cliente.exceptions.EntityPersistenceException;

public class ValidacaoClienteNulo implements ValidacaoClienteNovo {

	@Override
	public void valida(ClienteVO cliente) {
		if(cliente == null) 
			throw new EntityPersistenceException("O cliente não pode ser nulo.");		
	}
}

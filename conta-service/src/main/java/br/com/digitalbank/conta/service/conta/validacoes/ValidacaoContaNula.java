package br.com.digitalbank.conta.service.conta.validacoes;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.exception.EntityPersistenceException;

public class ValidacaoContaNula implements ValidacaoContaNova {
	
	@Override
	public void valida(ContaVO conta) {
		if (conta == null)
			throw new EntityPersistenceException("A conta não pode ser nula.");
	}
}

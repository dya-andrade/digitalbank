package br.com.digitalbank.conta.services.conta.validacoes;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;

public interface ValidacaoContaNova {

	void valida(ContaVO conta);
}
package br.com.digitalbank.conta.service.conta.validacoes;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;

public interface ValidacaoContaNova {

	void valida(ContaVO conta);
}

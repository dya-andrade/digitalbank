package br.com.digitalbank.cliente.service.validacoes;

import br.com.digitalbank.cliente.data.vo.v1.ClienteVO;

public interface ValidacaoClienteNovo {

	void valida(ClienteVO cliente);
}

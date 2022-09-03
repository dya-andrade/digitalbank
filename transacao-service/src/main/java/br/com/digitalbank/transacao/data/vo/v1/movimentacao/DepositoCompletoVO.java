package br.com.digitalbank.transacao.data.vo.v1.movimentacao;

import br.com.digitalbank.transacao.data.vo.v1.transacao.TransacaoVO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class DepositoCompletoVO {

	private DepositoVO deposito;
	
	private TransacaoVO transacao;
}

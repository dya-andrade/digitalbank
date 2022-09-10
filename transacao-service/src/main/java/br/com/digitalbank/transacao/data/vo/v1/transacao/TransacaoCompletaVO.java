package br.com.digitalbank.transacao.data.vo.v1.transacao;

import br.com.digitalbank.transacao.data.vo.v1.movimentacao.MovimentacaoVO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class TransacaoCompletaVO {

	private MovimentacaoVO movimentacao;
	
	private TransacaoVO transacao;
}

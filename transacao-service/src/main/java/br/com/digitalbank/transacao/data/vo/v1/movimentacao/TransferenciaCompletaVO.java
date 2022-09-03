package br.com.digitalbank.transacao.data.vo.v1.movimentacao;

import br.com.digitalbank.transacao.data.vo.v1.transacao.TransacaoVO;
import lombok.Data;

@Data
public class TransferenciaCompletaVO {

	private TransferenciaVO transferencia;
	
	private TransacaoVO transacao;
}

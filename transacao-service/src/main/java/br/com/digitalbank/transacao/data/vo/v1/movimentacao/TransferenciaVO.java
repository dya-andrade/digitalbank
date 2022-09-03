package br.com.digitalbank.transacao.data.vo.v1.movimentacao;

import java.io.Serializable;
import java.util.List;

import br.com.digitalbank.transacao.data.vo.v1.transacao.TransacaoVO;
import lombok.Data;

@Data 
public class TransferenciaVO extends MovimentacaoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TransacaoVO> transacoes;

}

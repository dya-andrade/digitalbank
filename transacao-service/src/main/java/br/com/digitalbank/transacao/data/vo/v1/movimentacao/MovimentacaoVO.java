package br.com.digitalbank.transacao.data.vo.v1.movimentacao;

import lombok.Data;

@Data  
public abstract class MovimentacaoVO {
	
	protected String cpfConta;
	protected String cpfTransacao;
	
}

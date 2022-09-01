package br.com.digitalbank.transacao.models.transacao;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransacaoCompleta {

	protected String cpfConta;
	protected String cpfTransacao;
	
	private BigDecimal valor;
}

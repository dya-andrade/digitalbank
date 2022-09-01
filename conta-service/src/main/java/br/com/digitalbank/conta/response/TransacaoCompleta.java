package br.com.digitalbank.conta.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransacaoCompleta {

	protected String cpfConta;
	protected String cpfTransacao;
	
	private BigDecimal valor;
}

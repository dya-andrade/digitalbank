package br.com.digitalbank.conta.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransacaoCompleta {

	private String cpfContaOrigem;
	private String cpfContaDestino;
	
	private BigDecimal valor;
}

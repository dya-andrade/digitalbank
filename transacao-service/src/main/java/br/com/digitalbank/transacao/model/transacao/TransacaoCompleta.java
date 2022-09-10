package br.com.digitalbank.transacao.model.transacao;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class TransacaoCompleta {
	
	private String cpfContaOrigem;
	private String cpfContaDestino;
	
	private BigDecimal valor;
}

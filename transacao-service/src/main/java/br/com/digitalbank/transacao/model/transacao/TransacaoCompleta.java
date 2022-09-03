package br.com.digitalbank.transacao.model.transacao;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class TransacaoCompleta {

	protected String cpfConta;
	protected String cpfTransacao;
	
	private BigDecimal valor;
}

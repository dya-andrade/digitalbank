package br.com.digitalbank.transacao.response;

import lombok.Data;

@Data
public class ContaCompleta {

	private Conta corrente;
	
	private Conta poupanca;
	
}

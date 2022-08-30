package br.com.digitalbank.conta.models.conta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ContaCompleta {

	private Corrente corrente;
	
	private Poupanca poupanca;
}

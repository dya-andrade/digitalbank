package br.com.digitalbank.conta.models.conta;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoConta {
	
	UNIVERSITARIA("Universitária"),
	
	COMUM("Comum");
	
	private String descricao;

	TipoConta(String descricao) {
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}
}

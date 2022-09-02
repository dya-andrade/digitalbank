package br.com.digitalbank.conta.models.movimentacao;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoMovimentacao {

	ANUAL("Anual", 12),

	MENSAL("Mensal", 1);

	private String descricao;

	private Integer tempoEmMes;

	TipoMovimentacao(String descricao, Integer tempoEmMes) {
		this.descricao = descricao;
		this.tempoEmMes = tempoEmMes;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

	public Integer getTempoEmMes() {
		return tempoEmMes;
	}
}

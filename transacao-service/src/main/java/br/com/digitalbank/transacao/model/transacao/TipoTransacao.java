package br.com.digitalbank.transacao.model.transacao;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoTransacao {
	
	PIX("Pix"),
	
	BOLETO("Boleto"),
	
	QRCODE("QRCode"),
	
	TED("TED");

	private String descricao;

	TipoTransacao(String descricao) {
		this.descricao = descricao;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

}

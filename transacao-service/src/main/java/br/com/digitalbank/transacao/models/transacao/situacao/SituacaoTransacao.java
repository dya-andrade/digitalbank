package br.com.digitalbank.transacao.models.transacao.situacao;

import java.math.BigDecimal;

import br.com.digitalbank.transacao.exceptions.DomainException;
import br.com.digitalbank.transacao.models.transacao.Transacao;

public abstract class SituacaoTransacao {

	public BigDecimal calculaValorTarifaExtra(Transacao transacao) {
		return BigDecimal.ZERO;
	}
	
	public void aprova(Transacao transacao) {
		throw new DomainException("Transação não pode ser aprovada!");
	}
	
	public void reprova(Transacao transacao) {
		throw new DomainException("Transação não pode ser reprovada!");
	}
	
	public void finaliza(Transacao transacao) {
		throw new DomainException("Transação não pode ser finalizada!");
	}
}

package br.com.digitalbank.conta.services.movimentacao.validacoes;

import java.math.BigDecimal;

import br.com.digitalbank.conta.exceptions.ValidationException;
import br.com.digitalbank.conta.models.conta.Conta;
import br.com.digitalbank.conta.models.movimentacao.Movimentacao;
import br.com.digitalbank.conta.models.movimentacao.TipoMovimentacao;

public class ValidacaoPercentualMovimentacao implements ValidacaoMovimentacao {


	@Override
	public void valida(Conta conta, Movimentacao movimentacao) {	
		Double percentual;
		
		if(movimentacao.getTipoMovimentacao().equals(TipoMovimentacao.ANUAL))
			percentual = 8.0;
		else
			percentual = 5.0;
		
		if (movimentacao.getPercentual().compareTo(new BigDecimal(percentual)) > 0) 
			throw new ValidationException("A movimentação não pode ser superior a " + percentual + "% de percentual!");
		
	}
}

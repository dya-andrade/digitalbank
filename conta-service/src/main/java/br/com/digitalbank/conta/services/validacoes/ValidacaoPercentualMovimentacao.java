package br.com.digitalbank.conta.services.validacoes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import br.com.digitalbank.conta.exceptions.ValidationException;
import br.com.digitalbank.conta.models.conta.Conta;
import br.com.digitalbank.conta.models.movimentacao.Movimentacao;
import br.com.digitalbank.conta.models.movimentacao.TipoMovimentacao;

public class ValidacaoPercentualMovimentacao implements ValidacaoMovimentacao {


	@Override
	public void valida(Conta conta, Movimentacao movimentacao) {
		
		BigDecimal percentualTarifa = movimentacao.getValor().divide(conta.getValor(), RoundingMode.HALF_UP);
		
		Double percentual;
		
		if(movimentacao.getTipoMovimentacao().equals(TipoMovimentacao.ANUAL))
			percentual = 0.08;
		else
			percentual = 0.05;
		
		if (percentualTarifa.compareTo(new BigDecimal(percentual)) > 0) 
			throw new ValidationException("A movimentação não pode ser superior a " + (percentual * 10) + "% do valor da conta!");
		
	}
}

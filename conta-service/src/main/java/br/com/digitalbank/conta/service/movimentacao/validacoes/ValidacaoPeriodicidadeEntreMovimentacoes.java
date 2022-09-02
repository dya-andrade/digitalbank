package br.com.digitalbank.conta.service.movimentacao.validacoes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.digitalbank.conta.exception.ValidationException;
import br.com.digitalbank.conta.model.conta.Conta;
import br.com.digitalbank.conta.model.movimentacao.Movimentacao;

public class ValidacaoPeriodicidadeEntreMovimentacoes implements ValidacaoMovimentacao {

	@Override
	public void valida(Conta conta, Movimentacao movimentacao) {

		if(conta.dataUltimaMovimentacao() != null) {
			LocalDate dataAtual = LocalDate.now();

			long mesesDesdeUltimaMovimentacao = ChronoUnit.MONTHS.between(conta.dataUltimaMovimentacao(), dataAtual);

			Integer tempoDoTipoMovimentacao = movimentacao.getTipoMovimentacao().getTempoEmMes();
			
			if (mesesDesdeUltimaMovimentacao < tempoDoTipoMovimentacao)
				throw new ValidationException("Intervalo entre as movimentações deve ser de no mínimo " + tempoDoTipoMovimentacao +" mes(es)!");
		}	
	}
}

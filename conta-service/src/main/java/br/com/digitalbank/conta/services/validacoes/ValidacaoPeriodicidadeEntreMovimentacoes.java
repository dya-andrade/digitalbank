package br.com.digitalbank.conta.services.validacoes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.digitalbank.conta.exceptions.ValidationException;
import br.com.digitalbank.conta.models.conta.Conta;
import br.com.digitalbank.conta.models.movimentacao.Movimentacao;

public class ValidacaoPeriodicidadeEntreMovimentacoes implements ValidacaoMovimentacao {

	@Override
	public void valida(Conta conta, Movimentacao movimentacao) {

		LocalDate dataAtual = LocalDate.now();

		long mesesDesdeUltimaMovimentacao = ChronoUnit.MONTHS.between(conta.dataUltimaMovimentacao(), dataAtual);

		Integer tempoDoTipoMovimentacao = movimentacao.getTipoMovimentacao().getTempo();
		
		if (mesesDesdeUltimaMovimentacao < tempoDoTipoMovimentacao)
			throw new ValidationException("Intervalo entre as movimentações deve ser de no mínimo " + tempoDoTipoMovimentacao +" mes(es)!");
	}
}

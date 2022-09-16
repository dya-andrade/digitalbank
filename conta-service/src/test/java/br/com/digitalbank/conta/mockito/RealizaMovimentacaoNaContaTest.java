package br.com.digitalbank.conta.mockito;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.digitalbank.conta.service.movimentacao.acoes.RealizaMovimentacaoNaConta;
import br.com.digitalbank.conta.service.movimentacao.validacoes.ValidacaoMovimentacao;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class RealizaMovimentacaoNaContaTest {
	
	@InjectMocks 
	private RealizaMovimentacaoNaConta realizaMovimentacaoNaConta;
	
	@Mock
	private List<ValidacaoMovimentacao> validacoes;

	@BeforeEach
	void setUpMocks() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testeRealizaMovimentacaoNaContaNull() {
				
		realizaMovimentacaoNaConta.executaNovaMovimentacao(null, null);
	}
}

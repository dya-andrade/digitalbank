package br.com.digitalbank.cliente.mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.digitalbank.cliente.mocks.MockCliente;
import br.com.digitalbank.cliente.repository.ClienteRepository;
import br.com.digitalbank.cliente.service.acao.RealizaValidacaoCliente;
import br.com.digitalbank.cliente.service.validacao.ValidacaoClienteExiste;
import br.com.digitalbank.cliente.service.validacao.ValidacaoLocalizaCliente;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ValidacaoClienteTest {
	
	private MockCliente input;
	
	@InjectMocks
	private ValidacaoLocalizaCliente localizaCliente;
	
	@InjectMocks
	private RealizaValidacaoCliente realizaValidacaoCliente;

	@Mock
	private ClienteRepository repository;
	
	@Mock
	private ValidacaoClienteExiste validacaoClienteExiste;
		

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockCliente();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testeValidacaoLocalizaCliente() {
		
		var entity = input.mockEntity(1l);
		
		Mockito.when(repository.findByCpf(entity.getCpf())).thenReturn(Optional.of(entity));
		
		var cliente = localizaCliente.valida(entity.getCpf());
		
		assertNotNull(cliente);
	}
	
	@Test
	void testeRealizaValidacaoCliente() {
		
		var entity = input.mockEntity(1l);
		
		Mockito.when(validacaoClienteExiste.valida(entity.getCpf())).thenReturn(entity);
		
		var cliente = realizaValidacaoCliente.validaEBuscaClienteExistente(entity.getCpf());
				
		assertNotNull(cliente);
	}
}

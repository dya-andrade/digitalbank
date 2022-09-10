package br.com.digitalbank.conta.mqqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.digitalbank.conta.repository.CorrenteRepository;
import br.com.digitalbank.conta.response.TransacaoCompleta;
import br.com.digitalbank.conta.service.conta.acao.RealizaValidacaoConta;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AutorizaTransacaoSubscriber {
	
	private final RealizaValidacaoConta realizaValidacaoConta;
	
	private final CorrenteRepository repository;

	@RabbitListener(queues = "${mq.queues.auth-transacao}")
	public void autorizaTransacao(@Payload String payLoad) {
		try {
			
			var mapper = new ObjectMapper();
			
			TransacaoCompleta dados = mapper.readValue(payLoad, TransacaoCompleta.class);

			var contaOrigem = realizaValidacaoConta.validaEBuscaContaExistente(dados.getCpfContaOrigem());
			var contaDestino = realizaValidacaoConta.validaEBuscaContaExistente(dados.getCpfContaDestino());
			
			contaOrigem.getCorrente().retiraValorTransacao(dados.getValor());
			contaDestino.getCorrente().adicionaValorTransacao(dados.getValor());
			
			repository.save(contaOrigem.getCorrente());
			repository.save(contaDestino.getCorrente());
			
			System.out.println("Transação concluída!");
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

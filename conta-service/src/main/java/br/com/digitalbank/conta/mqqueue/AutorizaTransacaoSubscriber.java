package br.com.digitalbank.conta.mqqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.digitalbank.conta.response.TransacaoCompleta;

public class AutorizaTransacaoSubscriber {

	@RabbitListener(queues = "${mq.queues.auth-transacao}")
	public void autorizaTransacao(@Payload String payLoad) {
		try {
			
			var mapper = new ObjectMapper();
			
			TransacaoCompleta dados = mapper.readValue(payLoad, TransacaoCompleta.class);

			System.out.println(dados.getCpfTransacao());
			System.out.println(dados.getCpfConta());

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

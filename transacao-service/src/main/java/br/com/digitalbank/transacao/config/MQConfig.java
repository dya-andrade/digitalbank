package br.com.digitalbank.transacao.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

	@Value("${mq.queues.auth-transacao}")
	private String authTransacaoFila;
	
	@Bean
	public Queue queueEmissaoCartoes(){ //para onde irá publicar as mensagens
		return new Queue(authTransacaoFila, true); //nome da fila e se ela é duravél
	}
}

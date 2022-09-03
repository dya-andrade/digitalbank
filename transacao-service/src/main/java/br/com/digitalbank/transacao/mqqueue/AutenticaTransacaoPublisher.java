package br.com.digitalbank.transacao.mqqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.digitalbank.transacao.model.transacao.TransacaoCompleta;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AutenticaTransacaoPublisher {

	private final RabbitTemplate rabbitTemplate;
	private final Queue queueAuthTransacao;

	public void autenticaTransacao(TransacaoCompleta dados) {
		var json = converteParaJson(dados);
		rabbitTemplate.convertAndSend(queueAuthTransacao.getName(), json);
	}

	private String converteParaJson(TransacaoCompleta dados) {
		ObjectMapper mapper = new ObjectMapper();

		String json = "";

		try {
			json = mapper.writeValueAsString(dados);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}
}

package br.com.digitalbank.gateway.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayCloudConfig {

	//configurações no YML de rotas
	
	//passa o nome do serviço registrado no eureka, quando a request chega no gateway, acessa
	// a eureka, e a eureka encontra a location e balancea as cargas entre as diferentes instancias
	
}

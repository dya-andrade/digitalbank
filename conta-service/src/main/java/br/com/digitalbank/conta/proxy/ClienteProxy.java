package br.com.digitalbank.conta.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.digitalbank.conta.response.Cliente;

@FeignClient(name = "cliente-service", url = "http://localhost:8765/cliente-service/v1")
public interface ClienteProxy {
	
	@GetMapping(value = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Cliente buscaClientePorCpf(@PathVariable(value = "cpf") String cpf);
}

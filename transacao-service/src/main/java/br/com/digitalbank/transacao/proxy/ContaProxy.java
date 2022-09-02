package br.com.digitalbank.transacao.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.digitalbank.transacao.response.ContaCompleta;

@FeignClient(name = "conta-service/v1")
public interface ContaProxy {

	@GetMapping(value = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ContaCompleta buscaContaPorCpf(@PathVariable(value = "cpf") String cpf);
}

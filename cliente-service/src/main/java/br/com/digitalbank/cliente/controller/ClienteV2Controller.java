package br.com.digitalbank.cliente.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalbank.cliente.data.vo.v2.ClienteVO2;
import br.com.digitalbank.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cliente-service/v2")
@RequiredArgsConstructor
public class ClienteV2Controller {
	
	private final ClienteService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ClienteVO2 criaClienteV2(@RequestBody @Valid ClienteVO2 vo2) {
		return service.criaClienteV2(vo2);
	}
	
	@GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClienteVO2 buscaClientePorCpfV2(@PathVariable(value = "cpf") String cpf) {
		return service.buscaClientePorCpfV2(cpf);
	}
}

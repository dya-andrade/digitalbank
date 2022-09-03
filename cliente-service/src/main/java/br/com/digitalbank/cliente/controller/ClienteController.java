package br.com.digitalbank.cliente.controller;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalbank.cliente.data.vo.v1.ClienteVO;
import br.com.digitalbank.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cliente-service/v1")
@RequiredArgsConstructor
public class ClienteController {
	
	private final ClienteService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ClienteVO criaCliente(@RequestBody @Valid ClienteVO vo) {
		return service.criaCliente(vo);
	}
	
	@GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClienteVO buscaClientePorCpf(@PathVariable(value = "cpf") String cpf) {
		return service.buscaClientePorCpf(cpf);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagedModel<EntityModel<ClienteVO>>> listaTodosClientes(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "tamanho", defaultValue = "5") Integer tamanho,
			@RequestParam(value = "direcao", defaultValue = "asc") String direcao){
		
		var sortDirection = "desc".equalsIgnoreCase(direcao) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(sortDirection, "nomeCompleto"));

		if (nome.isBlank())
			return ResponseEntity.ok(service.listaTodosClientes(pageable));
		else
			return ResponseEntity.ok(service.listaTodosClientesPorNome(nome, pageable));
	}
	
	@PatchMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClienteVO desativaCliente(@PathVariable(value = "cpf") String cpf) {
		return service.desativaCliente(cpf);
	}

}

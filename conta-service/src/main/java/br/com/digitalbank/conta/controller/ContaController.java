package br.com.digitalbank.conta.controller;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaCompletaVO;
import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.service.conta.ContaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/conta-service/v1")
@RequiredArgsConstructor
public class ContaController {

	private final ContaService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ContaCompletaVO criaConta(@RequestBody @Valid ContaVO vo) {
		return service.criaConta(vo);
	}
	
	@GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ContaCompletaVO buscaContaPorCpf(@PathVariable(value = "cpf") String cpf) {
		return service.buscaContaPorCpf(cpf);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PagedModel<EntityModel<ContaCompletaVO>> listaTodasContas(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "tamanho", defaultValue = "5") Integer tamanho,
			@RequestParam(value = "direcao", defaultValue = "asc") String direcao){
		
		var sortDirection = "desc".equalsIgnoreCase(direcao) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(sortDirection, "cpfCliente"));
		
		return service.listaTodasContas(pageable);
	}
	
}

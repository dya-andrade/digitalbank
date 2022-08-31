package br.com.digitalbank.conta.controllers;

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
import br.com.digitalbank.conta.services.conta.ContaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/conta-service/v1")
@RequiredArgsConstructor
public class ContaController {

	private final ContaService service;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ContaCompletaVO create(@RequestBody @Valid ContaVO vo) {
		return service.create(vo);
	}
	
	@GetMapping(value = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ContaCompletaVO findByCpf(@PathVariable(value = "cpf") String cpf) {
		return service.findByCpf(cpf);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public PagedModel<EntityModel<ContaCompletaVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size,
			@RequestParam(value = "direction", defaultValue = "asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "cpfCliente"));
		
		return service.findAll(pageable);
	}
	
}

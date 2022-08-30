package br.com.digitalbank.conta.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaCompletaVO;
import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.services.ContaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/conta-service/v1")
@RequiredArgsConstructor
public class ContaController {

	private final ContaService service;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ContaCompletaVO create(@RequestBody ContaVO vo) {
		return service.create(vo);
	}
	
	@GetMapping(value = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ContaCompletaVO findByCpf(@PathVariable(value = "cpf") String cpf) {
		return service.findByCpf(cpf);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ContaCompletaVO> findAll() {
		return service.findAll();
	}
	
}

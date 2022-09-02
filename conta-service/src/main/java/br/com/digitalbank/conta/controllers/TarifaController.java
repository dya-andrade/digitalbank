package br.com.digitalbank.conta.controllers;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalbank.conta.data.vo.conta.v1.CorrenteVO;
import br.com.digitalbank.conta.data.vo.movimentacao.v1.TarifaVO;
import br.com.digitalbank.conta.services.movimentacao.TarifaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/conta-service/tarifa/v1")
@RequiredArgsConstructor
public class TarifaController {

	private final TarifaService service;

	@PostMapping(value = "/{cpf}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public CorrenteVO criaTarifa(@RequestBody @Valid TarifaVO vo, @PathVariable(value = "cpf") String cpf) {
		return service.criaTarifa(vo, cpf);
	}

}

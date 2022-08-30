package br.com.digitalbank.conta.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalbank.conta.data.vo.conta.v1.PoupancaVO;
import br.com.digitalbank.conta.data.vo.movimentacao.v1.RendimentoCriadoVO;
import br.com.digitalbank.conta.services.RendimentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/conta-service/rendimento/v1")
@RequiredArgsConstructor
public class RendimentoController {

	private final RendimentoService service;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public PoupancaVO create(@RequestBody RendimentoCriadoVO vo) {
		return service.criaRendimento(vo);
	}

}

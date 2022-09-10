package br.com.digitalbank.transacao.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalbank.transacao.data.vo.v1.transacao.TransacaoCompletaVO;
import br.com.digitalbank.transacao.service.DepositoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transacao-service/deposito/v1")
@RequiredArgsConstructor
public class DepositoController {
	
	private final DepositoService service;
	

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TransacaoCompletaVO criaDeposito(@RequestBody TransacaoCompletaVO vo) {
		return service.criaDeposito(vo);
	}

}

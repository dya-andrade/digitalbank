package br.com.digitalbank.conta.service.movimentacao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalbank.conta.data.vo.conta.v1.CorrenteVO;
import br.com.digitalbank.conta.data.vo.movimentacao.v1.TarifaVO;
import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.model.conta.ContaCompleta;
import br.com.digitalbank.conta.model.movimentacao.Tarifa;
import br.com.digitalbank.conta.repository.CorrenteRepository;
import br.com.digitalbank.conta.service.conta.acao.RealizaValidacaoConta;
import br.com.digitalbank.conta.service.movimentacao.acoes.RealizaMovimentacaoNaConta;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TarifaService {

	private final CorrenteRepository correnteRepository;
	
	private final RealizaMovimentacaoNaConta realizaMovimentacaoNaConta;
	
	private final RealizaValidacaoConta realizaValidacaoConta;


	public CorrenteVO criaTarifa(TarifaVO vo, String cpf) {

		ContaCompleta contaCompleta = realizaValidacaoConta.validaEBuscaContaExistente(cpf);
		
		var corrente = contaCompleta.getCorrente();
		
		var tarifa = DozerMapper.parseObject(vo, Tarifa.class);
		
		realizaMovimentacaoNaConta.executaNovaMovimentacao(corrente, tarifa);

		var correnteVO = DozerMapper.parseObject(correnteRepository.save(corrente), CorrenteVO.class);

		return correnteVO;
	}

}

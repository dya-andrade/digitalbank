package br.com.digitalbank.conta.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalbank.conta.data.vo.conta.v1.CorrenteVO;
import br.com.digitalbank.conta.data.vo.movimentacao.v1.TarifaCriadaVO;
import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.models.conta.Corrente;
import br.com.digitalbank.conta.models.movimentacao.Tarifa;
import br.com.digitalbank.conta.repositories.CorrenteRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TarifaService extends MovimentacaoService<Tarifa, Corrente> {

	private final CorrenteRepository correnteRepository;

	public CorrenteVO criaTarifa(TarifaCriadaVO vo) {

		var corrente = DozerMapper.parseObject(vo.getCorrente(), Corrente.class);
		
		var tarifa = DozerMapper.parseObject(vo.getTarifa(), Tarifa.class);

		super.aplicaMovimentacao(corrente, tarifa);
		
		var correnteVO = DozerMapper.parseObject(correnteRepository.save(corrente), CorrenteVO.class);
		
		return correnteVO;
	}

}

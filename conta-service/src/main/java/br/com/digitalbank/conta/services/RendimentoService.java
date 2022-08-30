package br.com.digitalbank.conta.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalbank.conta.data.vo.conta.v1.PoupancaVO;
import br.com.digitalbank.conta.data.vo.movimentacao.v1.RendimentoCriadoVO;
import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.models.conta.Poupanca;
import br.com.digitalbank.conta.models.movimentacao.Rendimento;
import br.com.digitalbank.conta.repositories.PoupancaRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RendimentoService extends MovimentacaoService<Rendimento, Poupanca> {
	
	private final PoupancaRepository poupancaRepository;
	

	public PoupancaVO criaRendimento(RendimentoCriadoVO vo) {
	    var poupanca = DozerMapper.parseObject(vo.getPoupanca(), Poupanca.class);
		
		var rendimento = DozerMapper.parseObject(vo.getRendimento(), Rendimento.class);

		super.aplicaMovimentacao(poupanca, rendimento);
		
		var poupancaVO = DozerMapper.parseObject(poupancaRepository.save(poupanca), PoupancaVO.class);
		
		return poupancaVO;
	}
}

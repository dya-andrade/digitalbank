package br.com.digitalbank.conta.services.movimentacao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalbank.conta.data.vo.conta.v1.PoupancaVO;
import br.com.digitalbank.conta.data.vo.movimentacao.v1.RendimentoVO;
import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.models.conta.ContaCompleta;
import br.com.digitalbank.conta.models.movimentacao.Rendimento;
import br.com.digitalbank.conta.repositories.PoupancaRepository;
import br.com.digitalbank.conta.services.conta.acoes.RealizaValidacaoConta;
import br.com.digitalbank.conta.services.movimentacao.acoes.RealizaMovimentacao;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RendimentoService  {

	private final PoupancaRepository poupancaRepository;
	
	private final RealizaMovimentacao realizaMovimentacao;
	
	private final RealizaValidacaoConta realizaValidacaoConta;
		
	public PoupancaVO create(RendimentoVO vo, String cpf) {

		ContaCompleta contaCompleta = realizaValidacaoConta.validaEBuscaContaExistente(cpf);
		
		var poupanca = contaCompleta.getPoupanca();
		
		var rendimento = DozerMapper.parseObject(vo, Rendimento.class);
		
		realizaMovimentacao.executaNovaMovimentacao(poupanca, rendimento);

		var poupancaVO = DozerMapper.parseObject(poupancaRepository.save(poupanca), PoupancaVO.class);

		return poupancaVO;
	}
}
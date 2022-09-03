package br.com.digitalbank.conta.service.movimentacao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalbank.conta.data.vo.conta.v1.PoupancaVO;
import br.com.digitalbank.conta.data.vo.movimentacao.v1.RendimentoVO;
import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.model.conta.ContaCompleta;
import br.com.digitalbank.conta.model.movimentacao.Rendimento;
import br.com.digitalbank.conta.repository.PoupancaRepository;
import br.com.digitalbank.conta.service.conta.acao.RealizaValidacaoConta;
import br.com.digitalbank.conta.service.movimentacao.acoes.RealizaMovimentacaoNaConta;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RendimentoService  {

	private final PoupancaRepository poupancaRepository;

	private final RealizaValidacaoConta realizaValidacaoConta;
	
	private final RealizaMovimentacaoNaConta realizaMovimentacaoNaConta;
		
	public PoupancaVO criaRendimento(RendimentoVO vo, String cpf) {

		ContaCompleta contaCompleta = realizaValidacaoConta.validaEBuscaContaExistente(cpf);
		
		var poupanca = contaCompleta.getPoupanca();
		
		var rendimento = DozerMapper.parseObject(vo, Rendimento.class);
				
		realizaMovimentacaoNaConta.executaNovaMovimentacao(poupanca, rendimento);

		var poupancaVO = DozerMapper.parseObject(poupancaRepository.save(poupanca), PoupancaVO.class);

		return poupancaVO;
	}
}
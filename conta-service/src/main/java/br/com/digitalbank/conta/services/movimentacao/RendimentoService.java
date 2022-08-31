package br.com.digitalbank.conta.services.movimentacao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalbank.conta.data.vo.conta.v1.PoupancaVO;
import br.com.digitalbank.conta.data.vo.movimentacao.v1.RendimentoVO;
import br.com.digitalbank.conta.exceptions.ResourceNotFoundException;
import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.models.movimentacao.Rendimento;
import br.com.digitalbank.conta.repositories.PoupancaRepository;
import br.com.digitalbank.conta.services.movimentacao.acoes.RealizaMovimentacao;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RendimentoService  {

	private final PoupancaRepository poupancaRepository;
	
	private final RealizaMovimentacao realizaMovimentacao;
		
	public PoupancaVO create(RendimentoVO vo, String cpf) {

		var poupanca = poupancaRepository.findByCpfCliente(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma conta poupança encontrada com este CPF!"));

		var rendimento = DozerMapper.parseObject(vo, Rendimento.class);
		
		realizaMovimentacao.executa(poupanca, rendimento);

		var poupancaVO = DozerMapper.parseObject(poupancaRepository.save(poupanca), PoupancaVO.class);

		return poupancaVO;
	}
}
package br.com.digitalbank.conta.services.movimentacao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalbank.conta.data.vo.conta.v1.CorrenteVO;
import br.com.digitalbank.conta.data.vo.movimentacao.v1.TarifaVO;
import br.com.digitalbank.conta.exceptions.ResourceNotFoundException;
import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.models.conta.Corrente;
import br.com.digitalbank.conta.models.movimentacao.Tarifa;
import br.com.digitalbank.conta.repositories.CorrenteRepository;
import br.com.digitalbank.conta.services.movimentacao.acoes.RealizaMovimentacao;
import br.com.digitalbank.conta.services.movimentacao.validacoes.ValidacaoMovimentacao;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TarifaService {

	private final CorrenteRepository correnteRepository;
	
	private final RealizaMovimentacao realizaMovimentacao;


	public CorrenteVO create(TarifaVO vo, String cpf) {

		var corrente = correnteRepository.findByCpfCliente(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma conta corrente encontrada com este CPF!"));

		var tarifa = DozerMapper.parseObject(vo, Tarifa.class);
		
		realizaMovimentacao.executa(corrente, tarifa);

		var correnteVO = DozerMapper.parseObject(correnteRepository.save(corrente), CorrenteVO.class);

		return correnteVO;
	}

}

package br.com.digitalbank.transacao.service;

import org.springframework.stereotype.Service;

import br.com.digitalbank.transacao.data.vo.v1.movimentacao.DepositoVO;
import br.com.digitalbank.transacao.data.vo.v1.movimentacao.MovimentacaoVO;
import br.com.digitalbank.transacao.mapper.DozerMapper;
import br.com.digitalbank.transacao.model.movimentacao.Deposito;
import br.com.digitalbank.transacao.repository.DepositoRepository;

@Service
public class DepositoService extends MovimentacaoService <Deposito, DepositoVO, DepositoRepository> {

	@Override
	protected Deposito parseEntity(MovimentacaoVO vo) {
		return DozerMapper.parseObject(vo, Deposito.class);
	}
}

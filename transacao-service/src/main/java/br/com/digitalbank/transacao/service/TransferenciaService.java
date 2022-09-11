package br.com.digitalbank.transacao.service;

import org.springframework.stereotype.Service;

import br.com.digitalbank.transacao.data.vo.v1.movimentacao.MovimentacaoVO;
import br.com.digitalbank.transacao.data.vo.v1.movimentacao.TransferenciaVO;
import br.com.digitalbank.transacao.mapper.DozerMapper;
import br.com.digitalbank.transacao.model.movimentacao.Transferencia;
import br.com.digitalbank.transacao.repository.TransferenciaRepository;

@Service
public class TransferenciaService extends MovimentacaoService <Transferencia, TransferenciaVO, TransferenciaRepository> {

	@Override
	protected Transferencia parseEntity(MovimentacaoVO vo) {
		return DozerMapper.parseObject(vo, Transferencia.class);
	}
}

package br.com.digitalbank.transacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digitalbank.transacao.data.vo.v1.movimentacao.DepositoVO;
import br.com.digitalbank.transacao.data.vo.v1.transacao.TransacaoCompletaVO;
import br.com.digitalbank.transacao.data.vo.v1.transacao.TransacaoVO;
import br.com.digitalbank.transacao.mapper.DozerMapper;
import br.com.digitalbank.transacao.model.movimentacao.Deposito;
import br.com.digitalbank.transacao.model.transacao.Transacao;
import br.com.digitalbank.transacao.model.transacao.TransacaoCompleta;
import br.com.digitalbank.transacao.mqqueue.AutenticaTransacaoPublisher;
import br.com.digitalbank.transacao.repository.DepositoRepository;
import br.com.digitalbank.transacao.service.acao.FinalizaTransacao;
import br.com.digitalbank.transacao.service.acao.RealizaTransacao;

@Service
public class DepositoService {
	
	@Autowired
	private List<RealizaTransacao> realizaTransacao;
	
	@Autowired
	private FinalizaTransacao finalizaTransacao;
	
	@Autowired
	private AutenticaTransacaoPublisher autenticaTransacao;
	
	@Autowired
	private DepositoRepository repository;
	

	public TransacaoCompletaVO criaDeposito(TransacaoCompletaVO vo) {
		
		var deposito = DozerMapper.parseObject(vo.getMovimentacao(), Deposito.class);
		var transacao = DozerMapper.parseObject(vo.getTransacao(), Transacao.class);
		
		realizaTransacao.forEach(r -> r.executa(deposito, transacao));
		
		TransacaoCompleta transacaoCompleta = finalizaTransacao.finaliza(deposito, transacao);
		
		autenticaTransacao.autenticaTransacao(transacaoCompleta);
		
		Deposito depositoSalvo = repository.save(deposito);
		
		var depositoVO = DozerMapper.parseObject(depositoSalvo, DepositoVO.class);
		
		return new TransacaoCompletaVO(depositoVO, vo.getTransacao());
	}

}

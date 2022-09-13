package br.com.digitalbank.transacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalbank.transacao.data.vo.v1.movimentacao.MovimentacaoVO;
import br.com.digitalbank.transacao.data.vo.v1.transacao.TransacaoCompletaVO;
import br.com.digitalbank.transacao.mapper.DozerMapper;
import br.com.digitalbank.transacao.model.movimentacao.Movimentacao;
import br.com.digitalbank.transacao.model.transacao.Transacao;
import br.com.digitalbank.transacao.model.transacao.TransacaoCompleta;
import br.com.digitalbank.transacao.mqqueue.AutenticaTransacaoPublisher;
import br.com.digitalbank.transacao.service.acao.FinalizaTransacao;
import br.com.digitalbank.transacao.service.acao.ExecutaTransacao;

@Service
@Transactional
public abstract class MovimentacaoService <E extends Movimentacao, V extends MovimentacaoVO, R extends JpaRepository<E, Long>> {
	
	@Autowired
	private List<ExecutaTransacao> executaTransacao;
	
	@Autowired
	private FinalizaTransacao finalizaTransacao;
	
	@Autowired
	private AutenticaTransacaoPublisher autenticaTransacao;
	
	@Autowired
	protected R repository;
	
	protected abstract E parseEntity(MovimentacaoVO vo);


	public TransacaoCompletaVO criaTransacao(TransacaoCompletaVO vo) {
		
		E movimentacao = this.parseEntity(vo.getMovimentacao());
		var transacao = DozerMapper.parseObject(vo.getTransacao(), Transacao.class);
		
		executaTransacao.forEach(r -> r.executa(movimentacao, transacao));
		
		TransacaoCompleta transacaoCompleta = finalizaTransacao.finaliza(movimentacao, transacao);
		
		autenticaTransacao.autenticaTransacao(transacaoCompleta);
		
		repository.save(movimentacao);
				
		return new TransacaoCompletaVO(vo.getMovimentacao(), vo.getTransacao());
	}

}

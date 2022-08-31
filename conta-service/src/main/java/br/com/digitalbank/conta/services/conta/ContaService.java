package br.com.digitalbank.conta.services.conta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaCompletaVO;
import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.models.conta.ContaCompleta;
import br.com.digitalbank.conta.repositories.CorrenteRepository;
import br.com.digitalbank.conta.repositories.PoupancaRepository;
import br.com.digitalbank.conta.services.conta.acoes.RealizaConversaoConta;
import br.com.digitalbank.conta.services.conta.acoes.RealizaValidacaoConta;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ContaService {

	private final CorrenteRepository correnteRepository;

	private final PoupancaRepository poupancaRepository;

	private final RealizaValidacaoConta realizaValidacaoConta;
	
	private final RealizaConversaoConta realizaConversaoConta;
	
	
	private Logger logger = LoggerFactory.getLogger(ContaService.class);


	public ContaCompletaVO create(ContaVO vo) {

		logger.info("Persistindo e criando uma conta corrente e poupança.");
		
		realizaValidacaoConta.validaCriacaoDeContaNova(vo);
		
		var contaCompleta = realizaConversaoConta.converteEmContaCompletaEntity(vo);

		correnteRepository.save(contaCompleta.getCorrente());
		poupancaRepository.save(contaCompleta.getPoupanca());

		var contaCompletaVO = realizaConversaoConta.converteEmContaCompletaVO(contaCompleta);

		return contaCompletaVO;
	}

	public ContaCompletaVO findByCpf(String cpf) {

		logger.info("Busca conta corrente e poupança pelo CPF cliente.");

		var contaCompleta = realizaValidacaoConta.validaEBuscaContaExistente(cpf);

		var contaCompletaVO = realizaConversaoConta.converteEmContaCompletaVO(contaCompleta);		

		return contaCompletaVO;
	}

	public PagedModel<EntityModel<ContaCompletaVO>> findAll(Pageable pageable) {
		logger.info("Busca todas as contas cadastradas.");

		Page<ContaCompleta> contasPage = correnteRepository.findAllContas(pageable);
		
		var vosPages = realizaConversaoConta.converteEmContasCompletaPageVO(contasPage, pageable);

		return vosPages;
	}
}

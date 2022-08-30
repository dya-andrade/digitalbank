package br.com.digitalbank.conta.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalbank.conta.data.vo.conta.v1.ContaCompletaVO;
import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.data.vo.conta.v1.CorrenteVO;
import br.com.digitalbank.conta.data.vo.conta.v1.PoupancaVO;
import br.com.digitalbank.conta.exceptions.EntityPersistenceException;
import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.models.conta.Corrente;
import br.com.digitalbank.conta.models.conta.Poupanca;
import br.com.digitalbank.conta.repositories.CorrenteRepository;
import br.com.digitalbank.conta.repositories.PoupancaRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ContaService {
	
	private final CorrenteRepository correnteRepository;
	
	private final PoupancaRepository poupancaRepository;
	
	private Logger logger = LoggerFactory.getLogger(ContaService.class);

	
	public ContaCompletaVO create(ContaVO vo) {
		if(vo == null) 
			throw new EntityPersistenceException("A conta não pode ser nula.");
			
		Optional<Corrente> contaDuplicada = correnteRepository.findByCpfCliente(vo.getCpfCliente());
		
		if(contaDuplicada.isPresent()) 
			throw new EntityPersistenceException("Já existe uma conta com este CPF.");
		
		logger.info("Persistindo e criando uma conta corrente.");
		
		var corrente = DozerMapper.parseObject(vo, Corrente.class);
		var correnteVO = DozerMapper.parseObject(correnteRepository.save(corrente), CorrenteVO.class);
		
		logger.info("Persistindo e criando uma conta poupança.");
		
		var poupanca = DozerMapper.parseObject(vo, Poupanca.class);
		var poupancaVO = DozerMapper.parseObject(poupancaRepository.save(poupanca), PoupancaVO.class);
		
		return new ContaCompletaVO(correnteVO, poupancaVO);
	}

}

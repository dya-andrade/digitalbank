package br.com.digitalbank.cliente.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import br.com.digitalbank.cliente.data.vo.ClienteVO;
import br.com.digitalbank.cliente.data.vo.v1.ClienteVO1;
import br.com.digitalbank.cliente.data.vo.v2.ClienteVO2;
import br.com.digitalbank.cliente.mapper.DozerMapper;
import br.com.digitalbank.cliente.model.Cliente;
import br.com.digitalbank.cliente.repository.ClienteRepository;
import br.com.digitalbank.cliente.service.acao.AdicionaLinkEConversaoCliente;
import br.com.digitalbank.cliente.service.acao.RealizaValidacaoCliente;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {

	private Logger logger = LoggerFactory.getLogger(ClienteService.class);

	private final ClienteRepository repository;

	private final RealizaValidacaoCliente realizaValidacaoCliente;

	private final AdicionaLinkEConversaoCliente adicionaLinkEConversaoCliente;

	public ClienteVO1 criaCliente(ClienteVO1 vo) {

		var cliente = criaESalvaCliente(vo);

		return adicionaLinkEConversaoCliente.adicionaEConverteVO(cliente);
	}
	
	public ClienteVO2 criaClienteV2(@Valid ClienteVO2 vo2) {

		var cliente = criaESalvaCliente(vo2);
		
		return adicionaLinkEConversaoCliente.adicionaEConverteVO2(cliente);
	}
	
	private Cliente criaESalvaCliente(ClienteVO vo) {
		
		realizaValidacaoCliente.validaCriacaoNovoCliente(vo);

		logger.info("Persistindo uma entidade cliente");

		var cliente = DozerMapper.parseObject(vo, Cliente.class);
		
		repository.save(cliente);

		logger.info("Entidade cliente persitida");
		
		return cliente;
	}

	public ClienteVO1 buscaClientePorCpf(String cpf) {
		logger.info("Buscando uma entidade cliente!");

		var cliente = realizaValidacaoCliente.validaEBuscaClienteExistente(cpf);

		return adicionaLinkEConversaoCliente.adicionaEConverteVO(cliente);
	}
	
	public ClienteVO2 buscaClientePorCpfV2(String cpf) {
		logger.info("Buscando uma entidade cliente!");

		var cliente = realizaValidacaoCliente.validaEBuscaClienteExistente(cpf);

		return adicionaLinkEConversaoCliente.adicionaEConverteVO2(cliente);
	}

	public PagedModel<EntityModel<ClienteVO1>> listaTodosClientes(Pageable pageable) {
		logger.info("Buscando todas as entidades clientes!");

		Page<Cliente> clientesPage = repository.findAll(pageable);

		return adicionaLinkEConversaoCliente.adicionaEConvertePageVO(clientesPage, pageable);
	}

	public PagedModel<EntityModel<ClienteVO1>> listaTodosClientesPorNome(String nome, Pageable pageable) {
		logger.info("Buscando todas as entidades clientes por nome!");

		var clientesPage = repository.findAllByNome(nome, pageable);

		return adicionaLinkEConversaoCliente.adicionaEConvertePageVO(clientesPage, pageable);
	}

	public ClienteVO1 desativaCliente(String cpf) {
		logger.info("Desativando uma entidade cliente!");

		var cliente = realizaValidacaoCliente.validaEBuscaClienteExistente(cpf);

		repository.disableCliente(cliente.getId());

		return adicionaLinkEConversaoCliente.adicionaEConverteVO(cliente);
	}
}

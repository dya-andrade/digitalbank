package br.com.digitalbank.cliente.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import br.com.digitalbank.cliente.data.vo.v1.ClienteVO;
import br.com.digitalbank.cliente.mapper.DozerMapper;
import br.com.digitalbank.cliente.model.Cliente;
import br.com.digitalbank.cliente.repository.ClienteRepository;
import br.com.digitalbank.cliente.service.acoes.RealizaConversaoCliente;
import br.com.digitalbank.cliente.service.acoes.RealizaValidacaoCliente;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {

	private Logger logger = LoggerFactory.getLogger(ClienteService.class);

	private final ClienteRepository repository;

	private final RealizaValidacaoCliente realizaValidacaoCliente;

	private final RealizaConversaoCliente realizaConversaoCliente;

	public ClienteVO criaCliente(ClienteVO vo) {

		realizaValidacaoCliente.validaCriacaoNovoCliente(vo);

		logger.info("Persistindo uma entidade cliente");

		var cliente = DozerMapper.parseObject(vo, Cliente.class);

		logger.info("Entidade cliente persitida");

		return realizaConversaoCliente.converteEmClienteVO(cliente);
	}

	public ClienteVO buscaClientePorCpf(String cpf) {
		logger.info("Buscando uma entidade cliente!");

		var cliente = realizaValidacaoCliente.validaEBuscaClienteExistente(cpf);

		return realizaConversaoCliente.converteEmClienteVO(cliente);
	}

	public PagedModel<EntityModel<ClienteVO>> listaTodosClientes(Pageable pageable) {
		logger.info("Buscando todas as entidades clientes!");

		Page<Cliente> clientesPage = repository.findAll(pageable);

		return realizaConversaoCliente.converteEmClientesPageVO(clientesPage, pageable);
	}

	public PagedModel<EntityModel<ClienteVO>> listaTodosClientesPorNome(String nome, Pageable pageable) {
		logger.info("Buscando todas as entidades clientes por nome!");

		var clientesPage = repository.findAllByNome(nome, pageable);

		return realizaConversaoCliente.converteEmClientesPageVO(clientesPage, pageable);
	}

	public ClienteVO desativaCliente(String cpf) {
		logger.info("Desativando uma entidade cliente!");

		var cliente = realizaValidacaoCliente.validaEBuscaClienteExistente(cpf);

		repository.disableCliente(cliente.getId());

		return realizaConversaoCliente.converteEmClienteVO(cliente);
	}
}

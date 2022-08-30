package br.com.digitalbank.cliente.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import br.com.digitalbank.cliente.controller.ClienteController;
import br.com.digitalbank.cliente.data.vo.v1.ClienteVO;
import br.com.digitalbank.cliente.exceptions.EntityPersistenceException;
import br.com.digitalbank.cliente.exceptions.ResourceNotFoundException;
import br.com.digitalbank.cliente.mapper.DozerMapper;
import br.com.digitalbank.cliente.models.Cliente;
import br.com.digitalbank.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {
	
	private Logger logger = LoggerFactory.getLogger(ClienteService.class);

	private final ClienteRepository repository;
	
	private final PagedResourcesAssembler<ClienteVO> assembler; //montadora
	
	
	public ClienteVO create(ClienteVO vo) {
		if(vo == null) 
			throw new EntityPersistenceException("O cliente não pode ser nulo.");
			
		Optional<Cliente> clienteDuplicado = repository.findByCpf(vo.getCpf());
		
		if(clienteDuplicado.isPresent()) 
			throw new EntityPersistenceException("Já existe um cliente com este CPF.");
		
		logger.info("Persistindo uma entidade cliente");
		
		var cliente = DozerMapper.parseObject(vo, Cliente.class);
		
		vo = DozerMapper.parseObject(repository.save(cliente), ClienteVO.class);
		
		vo.add(linkTo(methodOn(ClienteController.class).findByCpf(vo.getCpf())).withSelfRel());
		
		logger.info("Entidade cliente persitida");
		
		return vo;
	}


	public ClienteVO findByCpf(String cpf) {
		logger.info("Buscando uma entidade cliente!");

		var cliente = repository.findByCpf(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma cliente encontrado com este CPF!"));
		
		var vo = DozerMapper.parseObject(cliente, ClienteVO.class);
		
		vo.add(linkTo(methodOn(ClienteController.class).findByCpf(vo.getCpf())).withSelfRel());
		
		return vo;
	}


	public PagedModel<EntityModel<ClienteVO>> findAll(Pageable pageable) {
		logger.info("Buscando todas as entidades clientes!");
		
		Page<Cliente> clientesPages = repository.findAll(pageable);
		
		var vosPages = clientesPages.map(c -> DozerMapper.parseObject(c, ClienteVO.class)); //converter person entity para VO

		vosPages.map(c -> c.add(
				linkTo(methodOn(ClienteController.class).findByCpf(c.getCpf())).withSelfRel())); //add links HATEOAS
	
		Link link = linkTo(methodOn(ClienteController.class)
				.findAll("", pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel(); //links das pages HATEAOS
		
		return assembler.toModel(vosPages, link);
	}


	public PagedModel<EntityModel<ClienteVO>> findAllByNome(String nome, Pageable pageable) {
	 logger.info("Buscando todas as entidades clientes por nome!");
		
		var clientesPages = repository.findAllByNome(nome, pageable);
		
		var vosPages = clientesPages.map(c -> DozerMapper.parseObject(c, ClienteVO.class)); //converter person entity para VO
		
		vosPages.map(c -> c.add(linkTo(methodOn(ClienteController.class).findByCpf(c.getCpf())).withSelfRel())); //add links HATEOAS
		
		Link link = linkTo(methodOn(ClienteController.class)
				.findAll(nome, pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel(); //links das pages HATEAOS
		
		return assembler.toModel(vosPages, link); 
	}

	public ClienteVO disableCliente(String cpf) {
		logger.info("Desativando uma entidade cliente!");

		var entity = repository.findByCpf(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma cliente encontrado com este CPF!"));
		
		repository.disableCliente(entity.getId());

		var vo = DozerMapper.parseObject(entity, ClienteVO.class);
		vo.add(linkTo(methodOn(ClienteController.class).findByCpf(cpf)).withSelfRel());
		
		return vo;
	}
}

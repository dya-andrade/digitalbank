package br.com.digitalbank.cliente.service.acao;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import br.com.digitalbank.cliente.controller.ClienteController;
import br.com.digitalbank.cliente.controller.ClienteV2Controller;
import br.com.digitalbank.cliente.data.vo.v1.ClienteVO1;
import br.com.digitalbank.cliente.data.vo.v2.ClienteVO2;
import br.com.digitalbank.cliente.mapper.DozerMapper;
import br.com.digitalbank.cliente.model.Cliente;

@Component
public class AdicionaLinkEConversaoCliente {
	
	@Autowired
	private PagedResourcesAssembler<ClienteVO1> assembler;

	public PagedModel<EntityModel<ClienteVO1>> adicionaEConvertePageVO(Page<Cliente> clientesPage,
			Pageable pageable){
		
		var vosPages = clientesPage.map(c -> DozerMapper.parseObject(c, ClienteVO1.class)); //converter person entity para VO

		vosPages.map(c -> c.add(
				linkTo(methodOn(ClienteController.class).buscaClientePorCpf(c.getCpf())).withSelfRel())); //add links HATEOAS
	
		Link link = linkTo(methodOn(ClienteController.class)
				.listaTodosClientes("", pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel(); //links das pages HATEAOS
		
		return assembler.toModel(vosPages, link);
	}
	
	public ClienteVO1 adicionaEConverteVO(Cliente cliente) {
		var vo = DozerMapper.parseObject(cliente, ClienteVO1.class);
		vo.add(linkTo(methodOn(ClienteController.class).buscaClientePorCpf(vo.getCpf())).withSelfRel());
		vo.add(linkTo(methodOn(ClienteController.class).desativaCliente(vo.getCpf())).withRel("Desativa"));
		return vo;
	}

	public ClienteVO2 adicionaEConverteVO2(Cliente cliente) {
		var vo = DozerMapper.parseObject(cliente, ClienteVO2.class);
		vo.add(linkTo(methodOn(ClienteV2Controller.class).buscaClientePorCpfV2(vo.getCpf())).withSelfRel());
		return vo;
	}
}

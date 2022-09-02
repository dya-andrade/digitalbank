package br.com.digitalbank.cliente.service.acoes;

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
import br.com.digitalbank.cliente.data.vo.v1.ClienteVO;
import br.com.digitalbank.cliente.mapper.DozerMapper;
import br.com.digitalbank.cliente.models.Cliente;

@Component
public class RealizaConversaoCliente {
	
	@Autowired
	private PagedResourcesAssembler<ClienteVO> assembler;

	public PagedModel<EntityModel<ClienteVO>> converteEmClientesPageVO(Page<Cliente> clientesPage,
			Pageable pageable){
		
		var vosPages = clientesPage.map(c -> DozerMapper.parseObject(c, ClienteVO.class)); //converter person entity para VO

		vosPages.map(c -> c.add(
				linkTo(methodOn(ClienteController.class).buscaClientePorCpf(c.getCpf())).withSelfRel())); //add links HATEOAS
	
		Link link = linkTo(methodOn(ClienteController.class)
				.listaTodosClientes("", pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel(); //links das pages HATEAOS
		
		return assembler.toModel(vosPages, link);
	}
	
	public ClienteVO converteEmClienteVO(Cliente cliente) {
		var vo = DozerMapper.parseObject(cliente, ClienteVO.class);
		vo.add(linkTo(methodOn(ClienteController.class).buscaClientePorCpf(vo.getCpf())).withSelfRel());
		
		return vo;
	}

}

package br.com.digitalbank.conta.services.conta.acoes;

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

import br.com.digitalbank.conta.controllers.ContaController;
import br.com.digitalbank.conta.data.vo.conta.v1.ContaCompletaVO;
import br.com.digitalbank.conta.data.vo.conta.v1.ContaVO;
import br.com.digitalbank.conta.data.vo.conta.v1.CorrenteVO;
import br.com.digitalbank.conta.data.vo.conta.v1.PoupancaVO;
import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.models.conta.ContaCompleta;
import br.com.digitalbank.conta.models.conta.Corrente;
import br.com.digitalbank.conta.models.conta.Poupanca;

@Component
public class RealizaConversaoConta {

	@Autowired
	private PagedResourcesAssembler<ContaCompletaVO> assembler;

	public ContaCompletaVO converteEmContaCompletaVO(ContaCompleta contaCompleta) {

		var correnteVO = DozerMapper.parseObject(contaCompleta.getCorrente(), CorrenteVO.class);
		var poupancaVO = DozerMapper.parseObject(contaCompleta.getPoupanca(), PoupancaVO.class);

		ContaCompletaVO contaCompletaVO = new ContaCompletaVO(correnteVO, poupancaVO);

		contaCompletaVO
				.add(linkTo(methodOn(ContaController.class).buscaContaPorCpf(correnteVO.getCpfCliente())).withSelfRel());

		return contaCompletaVO;
	}

	public ContaCompleta converteEmContaCompletaEntidade(ContaVO vo) {
		var corrente = DozerMapper.parseObject(vo, Corrente.class);
		var poupanca = DozerMapper.parseObject(vo, Poupanca.class);

		ContaCompleta contaCompleta = new ContaCompleta(corrente, poupanca);

		return contaCompleta;
	}

	public PagedModel<EntityModel<ContaCompletaVO>> converteEmContasCompletaPageVO(Page<ContaCompleta> contasPage,
			Pageable pageable) {

		var vosPages = contasPage
				.map(c -> new ContaCompletaVO(DozerMapper.parseObject(c.getCorrente(), CorrenteVO.class),
						DozerMapper.parseObject(c.getPoupanca(), PoupancaVO.class)));

		vosPages.map(c -> c
				.add(linkTo(methodOn(ContaController.class).buscaContaPorCpf(c.getCorrente().getCpfCliente())).withSelfRel()));

		Link link = linkTo(
				methodOn(ContaController.class).listaTodasContas(pageable.getPageNumber(), pageable.getPageSize(), "asc"))
						.withSelfRel();

		return assembler.toModel(vosPages, link);
	}

}

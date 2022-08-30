package br.com.digitalbank.conta.data.vo.movimentacao.v1;

import javax.validation.constraints.NotNull;

import br.com.digitalbank.conta.data.vo.conta.v1.PoupancaVO;
import lombok.Data;

@Data @NotNull
public class RendimentoCriadoVO {
	
	private RendimentoVO rendimento;
	
	private PoupancaVO poupanca;
}

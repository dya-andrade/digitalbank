package br.com.digitalbank.conta.data.vo.movimentacao.v1;

import javax.validation.constraints.NotNull;

import br.com.digitalbank.conta.data.vo.conta.v1.CorrenteVO;
import lombok.Data;

@Data @NotNull
public class TarifaCriadaVO {
	
	private TarifaVO tarifa;
	
	private CorrenteVO corrente;
}

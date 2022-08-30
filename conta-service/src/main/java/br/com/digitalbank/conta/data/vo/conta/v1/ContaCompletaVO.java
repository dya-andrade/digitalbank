package br.com.digitalbank.conta.data.vo.conta.v1;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @NotNull @AllArgsConstructor
public class ContaCompletaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CorrenteVO corrente;

	private PoupancaVO poupanca;
}

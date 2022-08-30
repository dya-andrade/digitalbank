package br.com.digitalbank.conta.data.vo.movimentacao.v1;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data @NotNull
public class TarifaVO extends MovimentacaoVO implements Serializable {

	private static final long serialVersionUID = 1L;

}

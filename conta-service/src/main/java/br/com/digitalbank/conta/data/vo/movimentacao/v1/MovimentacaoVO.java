package br.com.digitalbank.conta.data.vo.movimentacao.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.digitalbank.conta.model.movimentacao.TipoMovimentacao;
import lombok.Data;

@Data
public class MovimentacaoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	protected BigDecimal percentual;
	
	@NotNull
	protected LocalDate data;

	@NotNull
	protected TipoMovimentacao tipoMovimentacao;

}

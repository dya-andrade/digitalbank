package br.com.digitalbank.conta.data.vo.movimentacao.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.digitalbank.conta.models.movimentacao.TipoMovimentacao;
import lombok.Data;

@Data @NotNull
public class MovimentacaoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected BigDecimal valor;
	
	protected LocalDate data;

	@JsonProperty("tipo_movimentacao")
	protected TipoMovimentacao tipoMovimentacao;
}

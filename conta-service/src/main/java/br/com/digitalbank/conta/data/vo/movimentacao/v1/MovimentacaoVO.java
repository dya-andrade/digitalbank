package br.com.digitalbank.conta.data.vo.movimentacao.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.digitalbank.conta.models.movimentacao.TipoMovimentacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@JsonPropertyOrder({ "valor", "data", "tipoMovimentacao"})
public class MovimentacaoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected BigDecimal valor;
	
	protected LocalDate data;

	protected TipoMovimentacao tipoMovimentacao;
}

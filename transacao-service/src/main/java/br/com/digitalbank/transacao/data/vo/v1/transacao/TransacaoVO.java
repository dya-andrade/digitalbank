package br.com.digitalbank.transacao.data.vo.v1.transacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.digitalbank.transacao.model.transacao.TipoTransacao;
import lombok.Data;


@Data 
public class TransacaoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal valor;
	
	private LocalDate data;

	private TipoTransacao tipoTransacao;
}

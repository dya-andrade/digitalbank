package br.com.digitalbank.conta.data.vo.conta.v1;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.digitalbank.conta.models.conta.TipoConta;
import lombok.Data;

@Data 
public class ContaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	protected String cpfCliente;
	
	@NotNull
	protected BigDecimal valor;
	
	@NotNull
	protected TipoConta tipoConta;
}

package br.com.digitalbank.conta.data.vo.conta.v1;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.digitalbank.conta.models.conta.TipoConta;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@JsonPropertyOrder({ "cpfCliente", "valor", "tipoConta"})
public class ContaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String cpfCliente;
	
	protected BigDecimal valor;
	
	protected TipoConta tipoConta;

}

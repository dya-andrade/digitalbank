package br.com.digitalbank.conta.data.vo.conta.v1;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.digitalbank.conta.models.conta.TipoConta;
import lombok.Data;

@Data @NotNull 
@JsonPropertyOrder({ "cpf_cliente", "valor", "tipo_conta"})
public class ContaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("cpf_cliente")
	protected String cpfCliente;
	
	protected BigDecimal valor;
		
	@JsonProperty("tipo_conta")
	protected TipoConta tipoConta;
	
}

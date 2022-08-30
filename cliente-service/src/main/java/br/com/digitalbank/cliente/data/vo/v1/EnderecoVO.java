package br.com.digitalbank.cliente.data.vo.v1;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data 
public class EnderecoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String localidade;
	
	@NotBlank
	private String uf;
	
	@NotBlank
	private String cep;
}

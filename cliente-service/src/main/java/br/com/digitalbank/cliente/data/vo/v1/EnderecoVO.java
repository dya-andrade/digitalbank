package br.com.digitalbank.cliente.data.vo.v1;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data @NotBlank
@JsonPropertyOrder({ "logradouro", "bairro", "localidade", "uf", "cep"})
public class EnderecoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String logradouro;
	
	private String bairro;
	
	private String localidade;
	
	private String uf;
	
	private String cep;
}

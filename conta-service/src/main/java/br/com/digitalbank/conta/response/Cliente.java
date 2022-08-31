package br.com.digitalbank.conta.response;

import java.io.Serializable;

import lombok.Data;

@Data  
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cpf;

	private String nomeCompleto;

	private String email;

	private String telefone;

	private Boolean ativado;
}

package br.com.digitalbank.conta.models.conta;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.digitalbank.conta.models.movimentacao.MovimentacaoNaConta;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data @NoArgsConstructor 
public abstract class Conta implements MovimentacaoNaConta, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected String cpfCliente;
	
	protected BigDecimal valor;
		
	@Column(columnDefinition = "tinyint(1) default 1")
	protected Boolean ativada = this.ativada == null ? true : false;

	@Enumerated(EnumType.STRING)
	protected TipoConta tipoConta;
}

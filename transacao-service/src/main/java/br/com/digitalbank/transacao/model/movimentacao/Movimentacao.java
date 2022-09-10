package br.com.digitalbank.transacao.model.movimentacao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.digitalbank.transacao.model.transacao.Transacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data @NoArgsConstructor 
public abstract class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	protected String cpfContaOrigem;
	protected String cpfContaDestino;
	
	public abstract void adicionaTransacao(Transacao transacao);
}

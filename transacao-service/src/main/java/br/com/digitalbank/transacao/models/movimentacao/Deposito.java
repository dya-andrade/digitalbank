package br.com.digitalbank.transacao.models.movimentacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.digitalbank.transacao.models.transacao.Transacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "deposito")
@Data @NoArgsConstructor
public class Deposito extends Movimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "deposito_id")
	private List<Transacao> transacoes;

	@Override
	public void adicionaTransacao(Transacao transacao) {
		if (transacoes == null)
			transacoes = new ArrayList<Transacao>();
		
		transacoes.add(transacao);	
	}
}

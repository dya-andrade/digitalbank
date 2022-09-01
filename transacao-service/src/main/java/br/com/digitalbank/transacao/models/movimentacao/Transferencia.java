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
@Table(name = "transferencia")
@Data @NoArgsConstructor
public class Transferencia extends Movimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "transferencia_id")
	private List<Transacao> transacoes = new ArrayList<Transacao>();

	@Override
	public void adicionaTransacao(Transacao transacao) {
		transacoes.add(transacao);	
	}
}

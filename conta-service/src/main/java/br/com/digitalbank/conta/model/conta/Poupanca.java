package br.com.digitalbank.conta.model.conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.model.movimentacao.Movimentacao;
import br.com.digitalbank.conta.model.movimentacao.Rendimento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "poupanca")
@Data @NoArgsConstructor
public class Poupanca extends Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "poupanca_id")
	private List<Rendimento> rendimentos = new ArrayList<Rendimento>();
	
	private LocalDate ultimoRendimento;


	@Override
	public void movimentaNovoValor(BigDecimal novoValor) {
		super.setValor(super.getValor().add(novoValor));
		this.ultimoRendimento = LocalDate.now();
	}

	@Override
	public void adicionaMovimentacao(Movimentacao movimentacao) {
		Rendimento rendimento = DozerMapper.parseObject(movimentacao, Rendimento.class);
		rendimentos.add(rendimento);
	}
	
	@Override
	public LocalDate dataUltimaMovimentacao() {
		return this.ultimoRendimento;
	}
}

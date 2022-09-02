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
import br.com.digitalbank.conta.model.movimentacao.Tarifa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "corrente")
@Data @NoArgsConstructor
public class Corrente extends Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "corrente_id")
	private List<Tarifa> tarifas = new ArrayList<Tarifa>();

	private LocalDate ultimaTarifa;
	
	@Override
	public void movimentaNovoValor(BigDecimal novoValor) {
		super.valor = valor.subtract(novoValor);
		this.ultimaTarifa = LocalDate.now();
	}

	@Override
	public void adicionaMovimentacao(Movimentacao movimentacao) {
		Tarifa tarifa = DozerMapper.parseObject(movimentacao, Tarifa.class);
		tarifas.add(tarifa);
	}

	@Override
	public LocalDate dataUltimaMovimentacao() {
		return this.ultimaTarifa;
	}
}

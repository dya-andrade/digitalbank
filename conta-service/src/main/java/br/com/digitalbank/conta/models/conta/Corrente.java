package br.com.digitalbank.conta.models.conta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.digitalbank.conta.mapper.DozerMapper;
import br.com.digitalbank.conta.models.movimentacao.Movimentacao;
import br.com.digitalbank.conta.models.movimentacao.Tarifa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "corrente")
@Data @NoArgsConstructor
public class Corrente extends Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "corrente_id")
	private List<Tarifa> tarifas;

	private LocalDateTime ultimaTarifa;

	@Override
	public void movimentaNovoValor(BigDecimal percentual) {
		BigDecimal novoValor = super.valor.multiply(percentual);
		super.valor.subtract(novoValor);
		this.ultimaTarifa = LocalDateTime.now();
	}

	@Override
	public void adicionaMovimentacao(Movimentacao movimentacao) {

		Tarifa tarifa = DozerMapper.parseObject(movimentacao, Tarifa.class);

		if (tarifas == null)
			tarifas = new ArrayList<Tarifa>();

		tarifas.add(tarifa);
	}

	@Override
	public LocalDateTime dataUltimaMovimentacao() {
		return this.ultimaTarifa;
	}
}

package br.com.digitalbank.transacao.models.transacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.digitalbank.transacao.models.transacao.situacao.EmAnalise;
import br.com.digitalbank.transacao.models.transacao.situacao.SituacaoTransacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "transacao")
@Data @NoArgsConstructor
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal valor;
	
	private LocalDate data;

	@Enumerated(EnumType.STRING)
	private TipoTransacao tipoTransacao;
	
	private SituacaoTransacao situacao;
	
	public Transacao(BigDecimal valor, LocalDate data, TipoTransacao tipoTransacao) {
		this.valor = valor;
		this.data = data;
		this.tipoTransacao = tipoTransacao;
		this.situacao = new EmAnalise();
	}
	
	public void aplicaTarifaExtra() {
		BigDecimal valorDoDescontoExtra = this.situacao.calculaValorTarifaExtra(this);
		this.valor = this.valor.subtract(valorDoDescontoExtra);
	}
	
	public void aprova() {
		this.situacao.aprova(this);
	}
	
	public void reprova() {
		this.situacao.reprova(this);
	}
	
	public void finaliza() {
		this.situacao.finaliza(this);
	}
}

package br.com.digitalbank.conta.model.movimentacao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity 
@Table(name = "tarifa")
@NoArgsConstructor
public class Tarifa extends Movimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

}

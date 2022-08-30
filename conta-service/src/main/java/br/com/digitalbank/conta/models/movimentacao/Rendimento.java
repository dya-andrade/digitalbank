package br.com.digitalbank.conta.models.movimentacao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity 
@Table(name = "rendimento")
public class Rendimento extends Movimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

}

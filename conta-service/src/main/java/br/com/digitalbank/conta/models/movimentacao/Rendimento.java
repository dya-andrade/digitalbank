package br.com.digitalbank.conta.models.movimentacao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "rendimento")
@Data @NoArgsConstructor
public class Rendimento extends Movimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

}

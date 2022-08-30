package br.com.digitalbank.conta.data.vo.conta.v1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import br.com.digitalbank.conta.data.vo.movimentacao.v1.TarifaVO;
import lombok.Data;

@Data 
public class CorrenteVO extends ContaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<TarifaVO> tarifas;

	private LocalDate ultimaTarifa;

}

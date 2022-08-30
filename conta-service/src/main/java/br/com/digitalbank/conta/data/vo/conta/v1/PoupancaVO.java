package br.com.digitalbank.conta.data.vo.conta.v1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import br.com.digitalbank.conta.data.vo.movimentacao.v1.RendimentoVO;
import lombok.Data;

@Data 
public class PoupancaVO extends ContaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<RendimentoVO> rendimentos;

	private LocalDate ultimoRendimento;

}

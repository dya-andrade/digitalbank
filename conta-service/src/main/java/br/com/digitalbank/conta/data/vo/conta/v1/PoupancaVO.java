package br.com.digitalbank.conta.data.vo.conta.v1;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.digitalbank.conta.data.vo.movimentacao.v1.RendimentoVO;
import lombok.Data;

@Data @NotNull
public class PoupancaVO extends ContaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<RendimentoVO> rendimentos;

	private LocalDateTime ultimoRendimento;

}

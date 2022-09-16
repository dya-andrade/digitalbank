package br.com.digitalbank.cliente.data.vo.v2;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.digitalbank.cliente.data.vo.ClienteVO;
import lombok.Data;

@Data
public class ClienteVO2 extends ClienteVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String rg;

}

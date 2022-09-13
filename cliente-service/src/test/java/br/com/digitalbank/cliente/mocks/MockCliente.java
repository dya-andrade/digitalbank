package br.com.digitalbank.cliente.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalbank.cliente.data.vo.v1.ClienteVO;
import br.com.digitalbank.cliente.model.Cliente;

public class MockCliente {
	
	public Cliente mockEntity() {
		return mockEntity(0L);
	}

	public ClienteVO mockVO() {
		return mockVO(0L);
	}
	
	public List<Cliente> mockEntityList() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		for (long i = 0; i < 10; i++) {
			clientes.add(mockEntity(i));
		}
		return clientes;
	}

	public List<ClienteVO> mockVOList() {
		List<ClienteVO> vos = new ArrayList<ClienteVO>();
		for (long i = 0; i < 10; i++) {
			vos.add(mockVO(i));
		}
		return vos;
	}
	
	public Cliente mockEntity(Long number) {
		Cliente cliente = new Cliente();

		cliente.setId(number);
		cliente.setAtivado(true);
		cliente.setCpf("Cpf Test " + number);
		cliente.setEmail("Email Test " + number);
		cliente.setNomeCompleto("Nome Test " + number);
		cliente.setTelefone("Telefone Test " + number);
		
		return cliente;
	}

	public ClienteVO mockVO(Long number) {
		ClienteVO vo = new ClienteVO();

		vo.setAtivado(true);
		vo.setCpf("Cpf Test " + number);
		vo.setEmail("Email Test " + number);
		vo.setNomeCompleto("Nome Test " + number);
		vo.setTelefone("Telefone Test " + number);
		
		return vo;
	}
}

package br.com.digitalbank.cliente.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityPersistenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityPersistenceException() {
		super("Erro ao tentar persistir entidade!");
	}
	
	public EntityPersistenceException(String ex) {
		super(ex);
	}
}

package com.clientes.apirest.services;

import java.util.List;
import java.util.Optional;

import com.clientes.apirest.models.Cliente;

public interface ClienteService {

	List<Cliente> listarTodos();
	
	Optional<Cliente> listarProId(Long id);
	
	Cliente cadastrar(Cliente cliente);
	
	Cliente atualizar(Cliente cliente);
	
	void remover(Long id);
	
}

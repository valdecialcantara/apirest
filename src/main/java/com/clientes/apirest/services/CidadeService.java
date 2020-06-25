package com.clientes.apirest.services;

import java.util.List;
import java.util.Optional;

import com.clientes.apirest.models.Cidade;

public interface CidadeService {

	List<Cidade> listarTodos();
	
	Optional<Cidade> listarProId(Long id);
	
	List<Cidade> listarPorNome(String nome);
	
	List<Cidade> listarPorEstado(String estado);
	
	Cidade cadastrar(Cidade cidade);
	
	Cidade atualizar(Cidade cidade);
	
	void remover(Long id);
	
}

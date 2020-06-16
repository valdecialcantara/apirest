package com.clientes.apirest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientes.apirest.models.Cidade;
import com.clientes.apirest.repository.CidadeRepository;
import com.clientes.apirest.services.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository; 
	
	@Override
	public List<Cidade> listarTodos() {
		return this.cidadeRepository.findAll();
	}

	@Override
	public Optional<Cidade> listarProId(Long id) {
		return this.cidadeRepository.findById(id);
	}

	@Override
	public Cidade cadastrar(Cidade cidade) {
		return this.cidadeRepository.save(cidade);
	}

	@Override
	public Cidade atualizar(Cidade cidade) {
		return this.cidadeRepository.save(cidade);
	}

	@Override
	public void remover(Long id) {
		this.cidadeRepository.deleteById(id);
	}
}

package com.clientes.apirest.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientes.apirest.models.Cliente;
import com.clientes.apirest.repository.ClienteRepository;
import com.clientes.apirest.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository; 
	
	@Override
	public List<Cliente> listarTodos() {
		return this.clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> listarProId(Long id) {
		return this.clienteRepository.findById(id);
	}

	@Override
	public List<Cliente> listarPorNome(String nome) {
		return this.clienteRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	@Override
	public Cliente cadastrar(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	@Override
	public Cliente atualizar(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	@Override
	public void remover(Long id) {
		this.clienteRepository.deleteById(id);
	}

}

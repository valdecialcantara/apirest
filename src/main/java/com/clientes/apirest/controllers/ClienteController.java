package com.clientes.apirest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.apirest.models.Cliente;
import com.clientes.apirest.responses.Response;
import com.clientes.apirest.services.ClienteService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins="*")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/clientes")
	public ResponseEntity<Response<List<Cliente>>> listarTodos() {
		return ResponseEntity.ok(new Response<List<Cliente>>(this.clienteService.listarTodos()));
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<Response<Optional<Cliente>>> listarPorId(@PathVariable(name = "id") Long id ) {
		return ResponseEntity.ok(new Response<Optional<Cliente>>(this.clienteService.listarProId(id)));
	}
	
	@PostMapping("/cliente")
	public ResponseEntity<Response<Cliente>> cadastrar(@Valid @RequestBody Cliente cliente, BindingResult result ) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cliente>(erros));
		}
		return ResponseEntity.ok(new Response<Cliente>(this.clienteService.cadastrar(cliente)));
	}
	
	@PutMapping("/cliente")
	public ResponseEntity<Response<Cliente>> atualizar(@Valid @RequestBody Cliente cliente, BindingResult result ) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cliente>(erros));
		}
		return ResponseEntity.ok(new Response<Cliente>(this.clienteService.atualizar(cliente)));
	}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<Response<Integer>> remove(@PathVariable(name = "id") Long id ) {
		this.clienteService.remover(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}
	
}

package com.clientes.apirest.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.apirest.exception.ResourceNotFoundException;
import com.clientes.apirest.models.Cliente;
import com.clientes.apirest.responses.Response;
import com.clientes.apirest.services.ClienteService;
import com.clientes.apirest.util.Constantes;

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
	
	@GetMapping(path = {"/cliente/{id}"})
	public ResponseEntity<Cliente> listarPorId(@PathVariable(name = "id") Long id){
		return this.clienteService.listarProId(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = {"/cliente"})
	public ResponseEntity<Response<List<Cliente>>> listarPorNome(@RequestParam(name = "nome") String nome) 
			throws ResourceNotFoundException {
		List<Cliente> clientes = this.clienteService.listarPorNome(nome);
		if (clientes.isEmpty()) {
			throw new ResourceNotFoundException(Constantes.NOT_EMPTY_NOME_CLIENTE + nome);
		}
		return ResponseEntity.ok(new Response<List<Cliente>>(clientes));
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
	
	@PutMapping(value="/cliente/{id}")
	public ResponseEntity<Response<Cliente>> atualizar(@PathVariable(name = "id") Long id,
								@Valid @RequestBody Cliente cliente, BindingResult result){
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cliente>(erros));
		}
		return this.clienteService.listarProId(id)
		        .map(record -> {
		            record.setNome(cliente.getNome());
		            record.setDataNascimento(cliente.getDataNascimento());
		            record.setCidadeMora(cliente.getCidadeMora());
		            record.setSexo(cliente.getSexo());
		            record.setIdade(cliente.getIdade());
		            Cliente updated = this.clienteService.atualizar(record);
		            return ResponseEntity.ok().body(new Response<Cliente>(updated));
		        }).orElse(ResponseEntity.notFound().build());
	}	
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity remove(@PathVariable(name = "id") Long id ) {
		return this.clienteService.listarProId(id)
				.map(record -> {
					this.clienteService.remover(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}

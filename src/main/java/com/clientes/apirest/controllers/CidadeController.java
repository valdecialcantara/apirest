package com.clientes.apirest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.apirest.models.Cidade;
import com.clientes.apirest.responses.Response;
import com.clientes.apirest.services.CidadeService;

@RestController
@RequestMapping(path = "/api")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping("/cidades")
	public ResponseEntity<Response<List<Cidade>>> listarTodos() {
		return ResponseEntity.ok(new Response<List<Cidade>>(this.cidadeService.listarTodos()));
	}
	
	@GetMapping("/cidade/{id}")
	public ResponseEntity<Response<Optional<Cidade>>> listarPorId(@PathVariable(name = "id") Long id ) {
		return ResponseEntity.ok(new Response<Optional<Cidade>>(this.cidadeService.listarProId(id)));
	}
	
	@PostMapping("/cidade")
	public ResponseEntity<Response<Cidade>> cadastrar(@Valid @RequestBody Cidade Cidade, BindingResult result ) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cidade>(erros));
		}
		return ResponseEntity.ok(new Response<Cidade>(this.cidadeService.cadastrar(Cidade)));
	}
	
	@PutMapping("/cidade")
	public ResponseEntity<Response<Cidade>> atualizar(@Valid @RequestBody Cidade Cidade, BindingResult result ) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cidade>(erros));
		}
		return ResponseEntity.ok(new Response<Cidade>(this.cidadeService.atualizar(Cidade)));
	}
	
	@DeleteMapping("/cidade/{id}")
	public ResponseEntity<Response<Integer>> remove(@PathVariable(name = "id") Long id ) {
		this.cidadeService.remover(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}
	
}

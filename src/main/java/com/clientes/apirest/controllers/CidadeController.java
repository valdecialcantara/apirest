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
import com.clientes.apirest.models.Cidade;
import com.clientes.apirest.responses.Response;
import com.clientes.apirest.services.CidadeService;
import com.clientes.apirest.util.Constantes;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins="*")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping("/cidades")
	public ResponseEntity<Response<List<Cidade>>> listarTodos() {
		return ResponseEntity.ok(new Response<List<Cidade>>(this.cidadeService.listarTodos()));
	}
	
	@GetMapping(path = {"/cidade/{id}"})
	public ResponseEntity<Cidade> listarPorId(@PathVariable(name = "id") Long id){
		return this.cidadeService.listarProId(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}  
	
	@GetMapping(path = {"/cidade"})
	public ResponseEntity<Response<List<Cidade>>> listarPorNome(@RequestParam(name = "nome") String nome) 
			throws ResourceNotFoundException {
		List<Cidade> cidades = this.cidadeService.listarPorNome(nome);
		if (cidades.isEmpty()) {
			throw new ResourceNotFoundException(Constantes.NOT_FOUND_NOME_CIDADE + nome);
		}
		return ResponseEntity.ok(new Response<List<Cidade>>(cidades));
	} 

	@GetMapping(path = {"/cidade/UF"})
	public ResponseEntity<Response<List<Cidade>>> listarPorEstado(@RequestParam(name = "estado") String estado) 
			throws ResourceNotFoundException {
		List<Cidade> cidades = this.cidadeService.listarPorEstado(estado);
		if (cidades.isEmpty()) {
			throw new ResourceNotFoundException(Constantes.NOT_FOUND_ESTADO_CIDADE + estado);		
		}
		return ResponseEntity.ok(new Response<List<Cidade>>(cidades));
	} 
	
	@PostMapping("/cidade")
	public ResponseEntity<Response<Cidade>> cadastrar(@Valid @RequestBody Cidade cidade, BindingResult result ) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cidade>(erros));
		}
		return ResponseEntity.ok(new Response<Cidade>(this.cidadeService.cadastrar(cidade)));
	}
	
	@PutMapping(value="/cidade/{id}")
	public ResponseEntity<Response<Cidade>> atualizar(@PathVariable(name = "id") Long id,
								@Valid @RequestBody Cidade cidade, BindingResult result){
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cidade>(erros));
		}
		return this.cidadeService.listarProId(id)
		        .map(record -> {
		            record.setNome(cidade.getNome());
		            record.setEstado(cidade.getEstado());
		            Cidade updated = this.cidadeService.atualizar(record);
		            return ResponseEntity.ok().body(new Response<Cidade>(updated));
		        }).orElse(ResponseEntity.notFound().build());
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/cidade/{id}")
	public ResponseEntity remove(@PathVariable(name = "id") Long id ) {
		return this.cidadeService.listarProId(id)
				 .map(record -> {
					 this.cidadeService.remover(id);
					 return ResponseEntity.ok().build();
				 }).orElse(ResponseEntity.notFound().build());
		
	}
	
}

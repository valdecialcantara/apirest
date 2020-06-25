package com.clientes.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.clientes.apirest.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	List<Cidade> findByNomeContainingIgnoreCase(@Param("nome") @RequestParam("nome") String nome);
	
	List<Cidade> findByEstado(@Param("estado") @RequestParam("estado") String estado);
	
}

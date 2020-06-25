package com.clientes.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.clientes.apirest.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNomeContainingIgnoreCase(@Param("nome") @RequestParam("nome") String nome);

}
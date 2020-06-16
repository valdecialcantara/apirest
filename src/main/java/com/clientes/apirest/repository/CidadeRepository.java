package com.clientes.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientes.apirest.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}

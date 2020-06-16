package com.clientes.apirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.clientes.apirest.util.Util;


@Entity
@Table(name="TB_CLIENTE")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message = "Nome do cliente não pode ser vazio")
	private String nome;
	
	private String sexo;
	
	@NotNull(message = "Data de nascimento do cliente é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@Transient
	private Integer idade;
	
	@JoinColumn(name = "ID_CIDADE_MORA")
    @ManyToOne(targetEntity = Cidade.class)
	private Cidade cidadeMora;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getIdade() {
		return Util.calculaIdade(getDataNascimento());
	}

	public Cidade getCidadeMora() {
		return cidadeMora;
	}

	public void setCidadeMora(Cidade cidadeMora) {
		this.cidadeMora = cidadeMora;
	}
	
}

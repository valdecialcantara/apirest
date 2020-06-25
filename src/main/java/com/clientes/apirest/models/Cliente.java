package com.clientes.apirest.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.clientes.apirest.enums.Sexo;
import com.clientes.apirest.util.Constantes;
import com.clientes.apirest.util.Util;


@Entity
@Table(name="TB_CLIENTE")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = Constantes.NOT_EMPTY_NOME_CLIENTE)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = Constantes.NOT_NULL_SEXO_CLIENTE)
	@Column(name = "sexo", length = 1)
	private Sexo sexo;
	
	@NotNull(message = Constantes.NOT_NULL_DATA_NASCIMENTO_CLIENTE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_nascimento", nullable = true)
	private Date dataNascimento;
	
	@Transient
	private Integer idade;
	
	@ManyToOne
	@NotNull(message = Constantes.NOT_NULL_CIDADE_MORADIA_CLIENTE)
	@JoinColumn(name = "id_cidade_mora", referencedColumnName = "id")
	private Cidade cidadeMora;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
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

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Cidade getCidadeMora() {
		return cidadeMora;
	}

	public void setCidadeMora(Cidade cidadeMora) {
		this.cidadeMora = cidadeMora;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", dataNascimento=" + dataNascimento
				+ ", idade=" + idade + ", cidadeMora=" + cidadeMora + "]";
	}
	
}

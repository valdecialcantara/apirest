package com.clientes.apirest.util;

public class Constantes {
	
	private Constantes() {}
	
	public static final String NOT_FOUND_ESTADO_CIDADE = "Não existe cidade para este estado :: ";
	
	public static final String NOT_FOUND_NOME_CIDADE =  "Não existe cidade para este nome :: ";
	
	public static final String NOT_EMPTY_NOME_CIDADE = "Nome da cidade não pode ser vazio";
	
	public static final String NOT_EMPTY_ESTADO_CIDADE = "Estado não pode ser vazio";
	
	public static final String NOT_EMPTY_NOME_CLIENTE = "Nome do cliente não pode ser vazio";
	
	public static final String NOT_NULL_SEXO_CLIENTE = "Sexo do cliente é obrigatório";
	
	public static final String NOT_NULL_DATA_NASCIMENTO_CLIENTE = "Data de nascimento do cliente é obrigatório";
	
	public static final String NOT_NULL_CIDADE_MORADIA_CLIENTE = "Cidade de moradia do cliente é obrigatória";
	
	public static final String ENDPOINT_CIDADE = "/api/cidade";
	
	public static final String ENDPOINT_CLIENTE = "/api/cliente";
}
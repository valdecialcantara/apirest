package com.clientes.apirest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.clientes.apirest.enums.Sexo;
import com.clientes.apirest.models.Cidade;
import com.clientes.apirest.models.Cliente;
import com.clientes.apirest.repository.CidadeRepository;
import com.clientes.apirest.util.Constantes;
import com.clientes.apirest.util.Util;

public class ClienteServiceControllerTest extends AbstractTest {
   
	@Autowired
	CidadeRepository cidadeRepository;
	
	private static String data = "1958-12-17";
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testDeleteClienteSucesso() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(Constantes.ENDPOINT_CLIENTE + "/1")).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCreateClienteWhenHaventCidadeNascimento() throws Exception {
		Cliente cliente = new Cliente();
		Date dataNascimento = Util.parseDate(data);
		cliente.setDataNascimento(dataNascimento);
		cliente.setNome("Valdeci Bastos de Alcântara");
		cliente.setSexo(Sexo.M);
		String inputJson = super.mapToJson(cliente);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(Constantes.ENDPOINT_CLIENTE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
	   
		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

		String content = mvcResult.getResponse().getContentAsString();
		Map<String, List<Cliente>> clienteList = super.mapFromJson(content, Map.class);
		assertFalse(clienteList.get("erros").isEmpty());
	}
   
	@Test
	public void testCreateCliente() throws Exception {
		Cliente cliente = new Cliente();
		Optional<Cidade> cidade = cidadeRepository.findById(1L);
		if (cidade.isPresent()) {
			cliente.setCidadeMora(cidade.get());
		}
		Date dataNascimento = Util.parseDate(data);
		cliente.setDataNascimento(dataNascimento);
		cliente.setNome("Valdeci Bastos de Alcântara");
		cliente.setSexo(Sexo.M);
	   
		String inputJson = super.mapToJson(cliente);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(Constantes.ENDPOINT_CLIENTE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
	   
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testClientesList() throws Exception {
		String uri = "/api/clientes";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Map<String, List<Cliente>> clienteList = super.mapFromJson(content, Map.class);
		assertFalse(clienteList.get("data").isEmpty());
	}

	@Test
	public void testUpdateCliente() throws Exception {
		Cliente cliente = new Cliente();
		Optional<Cidade> cidade = cidadeRepository.findById(2L);
		if (cidade.isPresent()) {
			cliente.setCidadeMora(cidade.get());
		}
		Date dataNascimento = Util.parseDate(data);
		cliente.setDataNascimento(dataNascimento);
		cliente.setNome("Valdeci Silva de Alcântara");
		cliente.setSexo(Sexo.M);
      
		String inputJson = super.mapToJson(cliente);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(Constantes.ENDPOINT_CLIENTE + "/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
      
		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testClienteFindNome() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(Constantes.ENDPOINT_CLIENTE).param("nome", "Josefa")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Map<String, List<Cliente>> clienteList = super.mapFromJson(content, Map.class);
		assertFalse(clienteList.get("data").isEmpty());
	}
	
}
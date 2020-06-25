package com.clientes.apirest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.clientes.apirest.models.Cidade;
import com.clientes.apirest.util.Constantes;

public class CidadeServiceControllerTest extends AbstractTest {
   
   @Override
   @Before
   public void setUp() {
      super.setUp();
   }

   @Test
   @SuppressWarnings("unchecked")
   public void testCidadesList() throws Exception {
	   String uri = "/api/cidades";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			   .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   Map<String, List<Cidade>> cidadeList = super.mapFromJson(content, Map.class);
	   assertTrue(!cidadeList.get("data").isEmpty());
   }

   @Test
   @SuppressWarnings("unchecked")
   public void testCreateCidadeWhenHaventNome() throws Exception {
	   Cidade cidade = new Cidade();
	   cidade.setEstado("PE");
	   String inputJson = super.mapToJson(cidade);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(Constantes.ENDPOINT_CIDADE)
			   .contentType(MediaType.APPLICATION_JSON_VALUE)
			   .content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(400, status);

	   String content = mvcResult.getResponse().getContentAsString();
	   Map<String, List<Cidade>> cidadeList = super.mapFromJson(content, Map.class);
	   assertFalse(cidadeList.get("erros").isEmpty());
   }
   
   @Test
   public void testCreateCidade() throws Exception {
	   Cidade cidade = new Cidade();
	   cidade.setNome("Recife");
	   cidade.setEstado("PE");
	   String inputJson = super.mapToJson(cidade);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(Constantes.ENDPOINT_CIDADE)
			   .contentType(MediaType.APPLICATION_JSON_VALUE)
			   .content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
   }
   
   @Test
   public void testUpdateCidade() throws Exception {
      Cidade cidade = new Cidade();
      cidade.setNome("Jaboatão");
      cidade.setEstado("PE");
      
      String inputJson = super.mapToJson(cidade);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(Constantes.ENDPOINT_CIDADE+"/1")
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
   }

   @Test
   @SuppressWarnings("unchecked")
   public void testCidadeFindNome() throws Exception {
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(Constantes.ENDPOINT_CIDADE).param("nome", "Jaboa")
			   .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   Map<String, List<Cidade>> cidadeList = super.mapFromJson(content, Map.class);
	   assertFalse(cidadeList.get("data").isEmpty());
   }

   @Test
   @SuppressWarnings("unchecked")
   public void testCidadeFindEstado() throws Exception {
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(Constantes.ENDPOINT_CIDADE+"/UF").param("estado", "PB")
			   .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   Map<String, List<Cidade>> cidadeList = super.mapFromJson(content, Map.class);
	   assertFalse(cidadeList.get("data").isEmpty());
   }
   
   @Test
   public void testDeleteCidade() throws Exception {
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(Constantes.ENDPOINT_CIDADE+"/2")).andReturn();
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
   }
}
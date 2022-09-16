package br.com.digitalbank.cliente.testcontainers;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.digitalbank.cliente.config.TesteConfig;
import br.com.digitalbank.cliente.data.vo.v1.ClienteVO1;
import br.com.digitalbank.cliente.data.vo.v1.EnderecoVO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class ClienteControllerTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;

	private static ObjectMapper objectMapper;
	
	private ClienteVO1 vo;

	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	@Test
	@Order(0)
	public void autorizacao() {
		
		vo = new ClienteVO1();
		
		vo.setCpf("476.455.348-35");
		vo.setNomeCompleto("Dyane Andrade");
		vo.setEmail("dyane.aaraujo@gmail.com");
		vo.setTelefone("(11)92003-2417");

		EnderecoVO enderecoVO = new EnderecoVO();
		
		enderecoVO.setLogradouro("xxxxxxx");
		enderecoVO.setBairro("xxxxxxx");
		enderecoVO.setLocalidade("xxxxxxx");
		enderecoVO.setUf("SP");
		enderecoVO.setCep("xxxx-xxx");

		vo.setEndereco(enderecoVO);

		specification = new RequestSpecBuilder()
				.setBasePath(TesteConfig.URL).setPort(TesteConfig.PORTA)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
	}

	@Test
	@Order(1) 
	public void testeCriaCliente() throws JsonMappingException, JsonProcessingException, JSONException {

		var content = RestAssured.given()
				.spec(specification)
				.contentType(TesteConfig.APPLICATION_JSON)
					.body(vo)
				.when()
					.post()
				.then()
					.statusCode(200)
				.extract()
					.body().asString();

		var cliente = objectMapper.readValue(content, ClienteVO1.class);

		Assert.assertNotNull(cliente);
	}
	
	@Test
	@Order(2)
	public void testeBuscaClientePorCpf() throws JsonMappingException, JsonProcessingException {
				
		var content =
				RestAssured.given() 
				.spec(specification)
				.contentType(TesteConfig.APPLICATION_JSON)
					.pathParams("cpf", vo.getCpf()) 
				.when()
					.get("{cpf}")
				.then()
					.statusCode(200)
				.extract()
					.body().asString();
		
		var cliente = objectMapper.readValue(content, ClienteVO1.class);
		
		Assert.assertNotNull(cliente);
	}
	
	
	@Test
	@Order(3)
	public void testeDesativaCliente() throws JsonMappingException, JsonProcessingException {
				
		var content =
				RestAssured.given() 
				.spec(specification)
				.contentType(TesteConfig.APPLICATION_JSON)
					.pathParams("cpf", vo.getCpf()) 
				.when()
					.patch("{cpf}")
				.then()
					.statusCode(200)
				.extract()
					.body().asString();
		
		var cliente = objectMapper.readValue(content, ClienteVO1.class);
		
		Assert.assertNotNull(cliente);
		Assert.assertFalse(cliente.getAtivado());
	}
}

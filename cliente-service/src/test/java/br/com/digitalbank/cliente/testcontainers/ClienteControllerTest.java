package br.com.digitalbank.cliente.testcontainers;

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

import br.com.digitalbank.cliente.data.vo.v1.ClienteVO;
import br.com.digitalbank.cliente.mocks.MockCliente;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class ClienteControllerTest {

	private static RequestSpecification specification;

	private static ObjectMapper objectMapper;

	private static MockCliente input;

	@BeforeAll
	public static void setup() {
		input = new MockCliente();
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	@Test
	@Order(0)
	public void autorizacao() {

		specification = new RequestSpecBuilder()
				.setBasePath("/cliente-service/v1").setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
	}

	@Test
	@Order(1) 
	public void testeCriaCliente() throws JsonMappingException, JsonProcessingException {

		var vo = input.mockVO(1l);

		var content = RestAssured.given()
				.spec(specification)
				.contentType("application/json")
					.body(vo)
				.when()
					.post()
				.then()
					.statusCode(200)
				.extract()
					.body().asString();

		var cliente = objectMapper.readValue(content, ClienteVO.class);

		Assert.assertNotNull(cliente);
	}
	
	@Test
	@Order(2)
	public void testeBuscaClientePorCpf() throws JsonMappingException, JsonProcessingException {
				
		var vo = input.mockVO(1l);
		
		var content =
				RestAssured.given() 
				.spec(specification)
				.contentType("application/json")
					.pathParams("cpf", vo.getCpf()) 
				.when()
					.get("{cpf}")
				.then()
					.statusCode(200)
				.extract()
					.body().asString();
		
		var cliente = objectMapper.readValue(content, ClienteVO.class);
		
		Assert.assertNotNull(cliente);
	}
	
}

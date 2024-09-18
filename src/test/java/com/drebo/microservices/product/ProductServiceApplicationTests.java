package com.drebo.microservices.product;

import com.drebo.microservices.product.service.ProductService;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
//will run on random port and not configured port(8081)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@Autowired
	MongoDBContainer mongoDbContainer;

	@Autowired
	ProductService productService;

	//When app runs -> injects random port to variable
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@AfterEach
	void cleanup(){
		productService.deleteAllProducts();
	}


	@Test
	void isRunning() {
		assert mongoDbContainer.isRunning();
	}

	@Test
	void createProductTest(){
		String productDto = """
				{
				    "name": "test1",
				    "description": "test1",
				    "price": "69.99"
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(productDto).when()
				.post("/api/product").then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("test1"))
				.body("description", Matchers.equalTo("test1"))
				.body("price", Matchers.equalTo("69.99"));
	}


}

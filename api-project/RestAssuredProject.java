package raProject;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class RestAssuredProject {

	String ROOT_URI = "https://api.github.com";

	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	String ssh_key = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIEH64j2HCWHQVGCP5A3QwzXPaV5LYW2Vj7+lBWJI1vRJ gmx\\002xg0744@LAPTOP-SP6H05NL";
	int sshId;

	@BeforeClass
	public void setUp() {
		requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.addHeader("Authorization", "bearer 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")
				.setBaseUri(ROOT_URI).build();
	}
	@Test(priority = 1)
	// Test case using a DataProvider
	public void addKeys() {
		String reqBody = "{\"title\": \"TestKey\", \"key\": \"" + ssh_key + "\" }";
		Response response = given().spec(requestSpec) // Use requestSpec
				.body(reqBody) // Send request body
				.when().post("/user/keys"); // Send POST request
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody);
		sshId = response.then().extract().path("id"); // Assertions
		response.then().statusCode(201);
	}

	@Test(priority = 2)
	// Test case using a DataProvider
	public void getKeys() {
		Response response = given().spec(requestSpec) // Use requestSpec
				.when().get("/user/keys"); // Send GET Request
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody); // Assertions
		response.then().statusCode(200);
	}

	
	@Test(priority = 3)
	// Test case using a DataProvider
	public void deleteKeys() {
		Response response = given().spec(requestSpec) // Use requestSpec
				.pathParam("keyId", ssh_key).when().delete("/user/keys/{keyId}"); // Send GET Request
		Reporter.log(response.asString());
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody);		// Assertions
		response.then().statusCode(204);
	}

}
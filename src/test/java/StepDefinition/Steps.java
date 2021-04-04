package StepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;

public class Steps extends Utilities{
	ResponseSpecification respspec;
	static RequestSpecification resp;
	Response response;
	static String place_id;
	
	@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_Place_Payload_with(String name, String language, String address) throws Throwable {
	    System.out.println("Add Place Payload");
		respspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		resp=given().spec(requestSpecification())
		.body(TestDataBuild.addPlacePayload(name,language,address));
	}

	@When("^User call \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_call_with_http_request(String resource, String method) throws Throwable {
		APIResources apiResources=APIResources.valueOf(resource);
		String resourceValue=apiResources.getResources();
		
		
		if(method.equalsIgnoreCase("POST")) {
			System.out.println(resourceValue);
			System.out.println(method);
			System.out.println("Inside the Post API");
			response=resp.when().post(resourceValue)
					.then().spec(respspec).extract().response();
			System.out.println("After the Post API::"+response);
		}else if(method.equalsIgnoreCase("GET")) {
			System.out.println(resourceValue);
			response=resp.when().get(resourceValue)
					.then().spec(respspec).extract().response();
		}
		
		
		String responsestr=response.asString();
		System.out.println(responsestr);
	    
	}

	@Then("^The API Call get Success with status code (\\d+)$")
	public void the_API_Call_get_Success_with_status_code(int statusCode) throws Throwable {
		assertEquals(response.getStatusCode(), statusCode);
	    
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String keyValue, String expectedValue) throws Throwable {
		
		assertEquals(Utilities.getJsonPath(response, keyValue), expectedValue);
	    
	}
	
	@Then("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws Throwable {

		place_id=Utilities.getJsonPath(response, "place_id");
		resp=given().spec(requestSpecification()).queryParam("place_id",place_id);
		
		user_call_with_http_request(resource,"GET");
		
		String actualName=Utilities.getJsonPath(response, "name");
		 
		assertEquals(actualName,expectedName);
	    
	}
	
	@Given("^DeletePlace Payload$")
	public void deleteplace_Payload() throws Throwable {
	    System.out.println("PlaceId is ::"+place_id);
		resp=given().spec(requestSpecification()).body(TestDataBuild.deletePlacePayload(place_id));
		System.out.println("Response is change made by X ::"+resp);
		System.out.println("New line added by X");
		
		System.out.println("Made chnages in develop branch");
	    
	}


}

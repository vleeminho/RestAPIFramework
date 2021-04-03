package resources;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {
	static RequestSpecification res;
	
	public  RequestSpecification requestSpecification() throws Exception {
		//RestAssured.baseURI="https://rahulshettyacademy.com"; 
		
		if(res==null) {
			PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
			res=new RequestSpecBuilder().setBaseUri(ReadPropertyFile.getBaseURL())
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
		}
		
		return res;
	}
	
	public static String getJsonPath(Response response,String key) {
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		
		return js.get(key).toString();
	}

	
}

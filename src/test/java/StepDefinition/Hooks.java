package StepDefinition;

import TestRunner.Runner;
import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		Steps s=new Steps();
		
		if(s.place_id==null) {
			s.add_Place_Payload_with("Vrushali", "Marathi", "Pune");
			s.user_call_with_http_request("AddPlaceAPI", "POST");
			s.verify_place_id_created_maps_to_using("Vrushali", "GetPlaceAPI");
		}
		
		
	}
}

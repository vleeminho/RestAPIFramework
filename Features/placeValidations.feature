Feature: Validating AddPlace API

@AddPlace @Regression
Scenario Outline: Verify if place is being successfuly added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User call "AddPlaceAPI" with "POST" http request
	Then The API Call get Success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GetPlaceAPI"
	
Examples:
	|name             |language         |address              	 |
	|Vrushali Nagawade|French-IN        |29, side layout, cohen 09|
#	|Frontline house2 |Hindi-IN         |30, side layout, cohen 09|

@DeletePlace
Scenario: Verify if Delete place functionality is working
	Given DeletePlace Payload
	When User call "DeletePlaceAPI" with "POST" http request
	Then The API Call get Success with status code 200 
	And "status" in response body is "OK"
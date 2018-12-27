package testPack_voot;

import io.restassured.builder.RequestSpecBuilder;

public class testPOST_form_data {
	
	String apiBody = "{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}";

	
/*
	 // Building request by using requestSpecBuilder
	 RequestSpecBuilder builder = new RequestSpecBuilder();
	 
	 //Set API's Body
	 builder.setBody(apiBody);
	 
	 //Setting content type as application/json
	 builder.setContentType("application/json; charset=UTF-8");
	 
	 RequestSpecification requestSpec = builder.build();
	 
	 //Making post request with authentication or leave blank if you don't have credentials like: basic("","")
	 Response response = given().authentication().preemptive().basic({username}, {password})
	 .spec(requestSpec).when().post(restAPIUrl); 
	 
	 */

}

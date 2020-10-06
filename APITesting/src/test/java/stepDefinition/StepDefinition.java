package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

//import java.util.ArrayList;
import java.util.List;


//import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.Post;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification req1;
	ResponseSpecification respSpec;
	Response response;
	
	JsonPath js;
	TestDataBuild data =new TestDataBuild();
	// Utils ul= new Utils();since we areusing utils often
	
    @Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void add_place_payload_with_something_something_something(String name, String language, String address) throws Throwable {
     
    	 req1 =given().spec(requestSpecification()).body(data.add_place_payload(name, language, address));
    	
    }

	
    @When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void user_calls_something_with_something_http_request(String strArg1, String strArg2) throws Throwable {
	    	APIResources resourceAPI = APIResources.valueOf(strArg1);
	    	System.out.println(resourceAPI.getResource());
	    	
	    if(strArg2.equalsIgnoreCase("Post"))	
	    
	    	response = req1.when().post(resourceAPI.getResource());
	    else if (strArg2.equalsIgnoreCase("Get"))
	    	response = req1.when().get(resourceAPI.getResource());
	   	 
	   // respSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				//.expectStatusCode(200).build();
	   // response = req1.when().post(resourceAPI.getResource()).then().spec(respSpec).extract().response();

	    }

	    @Then("^the API call got success with status code 200$")
	    public void the_api_call_got_success_with_status_code_200() throws Throwable {
	    	
	    	assertEquals(response.getStatusCode(),200);
	    	//System.out.println(response.getStatusCode());
	    }

	    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	    public void something_in_response_body_is_something(String strArg1, String strArg2) throws Throwable {
	        /*String a = response.asString();
	         js = new JsonPath(a);
	        assertEquals(js.get(strArg1),strArg2);*/
	        
	       assertEquals(getJsonPath(response,strArg1),strArg2);
	        
	       // System.out.println(strArg1);
	       // System.out.println(strArg2);
	    }
	    
	    @And("^verify place_Idcreated maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	    public void verify_placeidcreated_maps_to_something_using_something(String name, String getPlaceAPI) throws Throwable {
	    	//hit add call
	    	//get place ID and hit get API
	    	//from get response check whether "name " in get response is same as "name " in ADD body
	    	
	       
	   	String place_id=getJsonPath(response,"place_id");
	    	 req1 =given().spec(requestSpecification()).queryParam("place_id", place_id);
	    	 
	    	user_calls_something_with_something_http_request( getPlaceAPI,"Get");
	    	String actualName=getJsonPath(response,"name");
	    	assertEquals(actualName,name);
	    
	    	 
	    	 
	    }

	   
	    
	    
}

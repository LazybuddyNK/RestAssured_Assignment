package sample;


import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;

import Pojo.pojoclass;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class sample_1 {

	
	@Test(enabled = true)
	public void CreateUser(ITestContext val) throws JsonProcessingException
	{
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
//		given().get("/IBM_HALDIA").then().statusCode(200).log().body();
		
		pojoclass obj = new pojoclass();
		obj.setUsername("Nk1234");
		obj.setFirstName("Nk");
		obj.setLastName("kr");
		obj.setEmail("asd@gk.dk");
		obj.setPassword("qwer");
		obj.setPhone("1234");


//		ObjectMapper objmapper = new ObjectMapper();
		
//		System.out.println(val.getAttribute("id").toString());
		
		
//		System.out.println(objmapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
//		
//		
//		
//	}
	

	  ObjectMapper objmapper = new ObjectMapper();
	  
	  System.out.println(objmapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));

	  given()
		.contentType(ContentType.JSON)
		.body(objmapper.writeValueAsString(obj)).
	  when()
		.post("/user").
	  then()
//		.statusCode(200)
		.log()
		.all();

    String usr = obj.getUsername();
    val.setAttribute("Username", usr);
    System.out.println(usr);
    
    String pass = obj.getPassword();
    val.setAttribute("Password", pass);
    System.out.println(pass);
    
}


@Test(dependsOnMethods = "CreateUser")
public void UpdateUser(ITestContext val) {

	  RestAssured.baseURI="https://petstore.swagger.io/v2";
	  
	  JSONObject details = new JSONObject();
	  pojoclass obj = new pojoclass();
	  
	  details.put("Email", obj.getEmail());
	  details.put("Firstname",obj.getFirstName());
	  details.put("Lastname",obj.getLastName());
	  details.put("Phone",obj.getLastName());
	  details.put("Password",obj.getPassword());
	  details.put("Username",obj.getUsername());

	  given()
		.contentType(ContentType.JSON)
		.body(details.toJSONString()).
	  when()
		.put("/user"+val.getAttribute("Username")).
	  then()
//		.statusCode(200)
		.log()
		.all();
	  }


 @Test(dependsOnMethods="UpdateUser")
 public void login(ITestContext val)  {

	  RestAssured.baseURI="https://petstore.swagger.io/v2";
	  
	  given()
	  	.queryParam("Username", val.getAttribute("Username"))
	  	.get("/user/login").
	  then()
//	  	.statusCode(200)
	  	.log()
	  	.all();
	  	


 }
 
 

 @Test(dependsOnMethods="login")
 public void logout(ITestContext val)  {

	  RestAssured.baseURI="https://petstore.swagger.io/v2";
	  
	  given()
	  	.get("/user/login").
	  then()
//	  	.statusCode(200)
	  	.log()
	  	.all();
	  	
 
 
 }
 

 @Test(dependsOnMethods="logout")
 public void Delete(ITestContext val)  {

	  RestAssured.baseURI="https://petstore.swagger.io/v2";
	  
	  String usrname = val.getAttribute("Username").toString();
	  
	  given()
	  	.delete("/user/" + usrname).
	  	
	  then()
//	  	.statusCode(202)
	  	.log()
	  	.all();
	  	

 }


}


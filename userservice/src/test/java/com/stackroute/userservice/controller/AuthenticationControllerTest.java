package com.stackroute.userservice.controller;

import java.util.HashMap;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.given;


//import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {
	
	@Test(priority=1)
		public void test_registerUser() {
	 HashMap data = new HashMap();
	 data.put("userId", "1026");
	 data.put("firstName", "Gotham");
	 data.put("lastName", "John");
	 data.put("password", "GJ@123");
	 Response res=
	  given().contentType("application/json").body(data).when().post("http://localhost:8081/api/books/registerUser")
	 .then().statusCode(201).log().body().extract().response();
	 String jsonString=res.asString();
	 Assert.assertEquals(jsonString.contains("User successfully registered to the App"),true);
		}
		
		@Test(priority=2)
		public void test_loginUser() {
	 HashMap data = new HashMap();
	 data.put("userId", "111");
	 data.put("password", "maya@123");
	 Response resp=
	 given().contentType("application/json").body(data).when().post("http://localhost:8081/api/books/login")
	 .then().statusCode(200).log().body().extract().response();
//	 String jString=resp.asString();
//	 Assert.assertEquals(jString.contains("User successfully logged in"),true);
	 }

		@Test(priority=3)
		public void test_loginUser1() {
	 HashMap data = new HashMap();
	 data.put("userId", "111");
	 data.put("password", "meena@123");
	 Response resp=
	 given().contentType("application/json").body(data).when().post("http://localhost:8081/api/books/login")
	 .then().statusCode(401).log().body().extract().response();
	 String jString=resp.asString();
	 Assert.assertEquals(jString.contains("User not found "),true);
		}
		
		@Test(priority=4)
		public void test_loginUser2() {
	 HashMap data = new HashMap();
	 data.put("userId", "");
	 data.put("password", "meena@123");
	 Response resp=
	 given().contentType("application/json").body(data).when().post("http://localhost:8081/api/books/login")
	 .then().statusCode(401).log().body().extract().response();
	 String jString=resp.asString();
	 Assert.assertEquals(jString.contains("User Id and Password are mandatory"),true);
		}
		
		@Test(priority=5)
		public void test_loginUser3() {
	 HashMap data = new HashMap();
	 data.put("userId", "");
	 data.put("password", "");
	 Response resp=
	 given().contentType("application/json").body(data).when().post("http://localhost:8081/api/books/login")
	 .then().statusCode(401).log().body().extract().response();
	 String jString=resp.asString();
	 Assert.assertEquals(jString.contains("User Id and Password are mandatory"),true);
		}
		
		@Test(priority=6)
		 public void test_registerUser1() {
		 HashMap data = new HashMap();
		 data.put("userId", "1011");
		 data.put("firstName", "Harikeshavan");
		 data.put("lastName", "KS");
		 data.put("password", "hari@123");
		 Response res=
		 given().contentType("application/json").body(data).when().post("http://localhost:8081/api/books/registerUser")
		 .then().statusCode(409).log().body().extract().response();
		 String jsonString=res.asString();
		 Assert.assertEquals(jsonString.contains("User id already exists"),true);
		}
		 
		 @Test(priority=7)
			
			public void test_registerUser_with_no_userid_or_password() {
		 HashMap data = new HashMap();
		 data.put("userId", "");
		 data.put("firstName", "rishi");
		 data.put("lastName", "sr");
		 data.put("password", "");
		 Response resp=
		 given().contentType("application/json").body(data).when().post("http://localhost:8081/api/books/registerUser")
		 .then().statusCode(400).log().body().extract().response();
		 String jString=resp.asString();
		 Assert.assertEquals(jString.contains("User Id and Password are mandatory"),true);
		 //given().auth().preemptive().basic(username, password).when().get("{yourApiURL}").then().statusCode(200);
			}	
}

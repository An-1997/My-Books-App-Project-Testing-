package com.stackroute.favouriteservice.controller;

import java.util.HashMap;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testng.annotations.*;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
	 

	 @Test(priority=1)
	public void saveBookTest() {
		 String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXNoIiwiaWF0IjoxNjEzODE0NDQzfQ.cqm8yMhgRlK8Jj2rhx4ZJGZllByh601156A1f-q8ov4";

		 

		HashMap data = new HashMap();
		data.put("id", 194);
		data.put("userId", "fredy");
		data.put("coverImage", "pic.jpg");
		data.put("title", "mello");
		data.put("authorName", "Gerrard");
		data.put("firstPublisYear",1896);
		data.put("editionCount", 21);
		
		 Response res=
				 
				 given().header("Authorization", "Bearer " + token).contentType("application/json").with().body(data).when().
				 post("http://localhost:8082/api/books/saveBookToMyFavourites")
				 .then().statusCode(201).log().body().extract().response();
	}
	 
	 //Book already added test case(Negative Test Case.
	 @Test(priority = 2)
	 public void test_BookAlreadyExists() {
		 
		 String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXNoIiwiaWF0IjoxNjEzODE0NDQzfQ.cqm8yMhgRlK8Jj2rhx4ZJGZllByh601156A1f-q8ov4";
		 
		 HashMap data = new HashMap();
			data.put("id", 330);
			data.put("userId", "Hanua");
			data.put("coverImage", "Hanu");
			data.put("title", "Hanua Mejo");
			data.put("authorName", "Amber");
			data.put("firstPublisYear",1559);
			data.put("editionCount", 13);
			
			 Response res=
					 
					 given().header("Authorization", "Bearer " + token).contentType("application/json").with().body(data).when().
					 post("http://localhost:8082/api/books/saveBookToMyFavourites")
					 .then().statusCode(409).log().body().extract().response();
	 }
	 
	 //Get the list of all favourite books.
	@Test(priority = 3)
			public void test_getMyFavouriteBooks() {
			 String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXNoIiwiaWF0IjoxNjEzODE0NDQzfQ.cqm8yMhgRlK8Jj2rhx4ZJGZllByh601156A1f-q8ov4";

		 given().header("Authorization", "Bearer " + token).contentType("application/json").when().get("http://localhost:8082/api/books/getMyFavouriteBooks")
		 .then().statusCode(200);
			}
	 
	//Delete books from favourites.
	 @Test(priority =4)
		public void test_deleteBooksFromFavourite() {
		 String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXNoIiwiaWF0IjoxNjEzODE0NDQzfQ.cqm8yMhgRlK8Jj2rhx4ZJGZllByh601156A1f-q8ov4";

	 Response res=
			 given().header("Authorization", "Bearer " + token).contentType("application/json").when().
			 delete("http://localhost:8082/api/books/deleteBookFromMyFavourites/194")
			 .then().statusCode(200).log().body().extract().response();

			 
		}
	 //Testing with  ID
	 @Test(priority =5)
		public void test_deleteBooksFailure() {
		 String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXNoIiwiaWF0IjoxNjEzODE0NDQzfQ.cqm8yMhgRlK8Jj2rhx4ZJGZllByh601156A1f-q8ov4";

	 Response res=
			 given().header("Authorization", "Bearer " + token).contentType("application/json").when().
			 delete("http://localhost:8082/api/books/deleteBookFromMyFavourites/54")
			 .then().statusCode(409).log().body().extract().response();

			 
		}
	 //Get my books failure
		@Test(priority = 6)
		public void test_getMyFavouriteBooksFailure() {
		 String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYXNoIiwiaWF0IjoxNjEzODE0NDQzfQ.cqm8yMhgRlK8Jj2rhx4ZJGZllByh601156A1f-q8ov4";

	 given().header("Authorization", "Bearer " + token).contentType("application/json").when().get("http://localhost:8082/api/books/getMyFavouriteBooks/662")
	 .then().statusCode(404);
		}
	 
	
		

	
}

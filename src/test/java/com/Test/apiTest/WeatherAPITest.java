package com.Test.apiTest;

import org.hamcrest.Matchers;
import org.testng.Reporter;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class WeatherAPITest {
	

	@Test
	public void getWeatherInfoOfLondonWithValidData() {

		RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/weather";
		String response = RestAssured.given().param("q", "Mumbai").param("appid", "17e5c69afcef0f16365a6c3b0cba4400").when().get().then().extract().asString();
		System.out.println("Response is :- " + response);

		//To print the logs in TestNg

		Reporter.log("Repose is "+response, true);


		ValidatableResponse res =  RestAssured.given().param("q", "Mumbai").param("appid", "17e5c69afcef0f16365a6c3b0cba4400").when().get().then();

		res.statusCode(200);
		
		Reporter.log("Status Code is Verified Sucessfully with 200 ", true);



		res.contentType(ContentType.JSON);

		String CountryName =  res.extract().response().path("sys.country");

		System.out.println("CountryName is ==== >> "+ CountryName);    // Getting the String from Json Format

		res.body("sys.country", Matchers.notNullValue());

		res.body("sys.country",Matchers.equalToIgnoringCase("IN"));
		
	
		Reporter.log("Country Name is Verified Sucessfully", true);


		String CityName = res.extract().response().path("name");

		System.out.println("CityName is ==== >> "+ CityName);

		res.body("name", Matchers.notNullValue());

		res.body("name",Matchers.equalToIgnoringCase("Mumbai"));

		Reporter.log("City Name is Verified Sucessfully", true);

	}
	
	@Test
	public void gettingverifyerrorMessages() {
		
		ValidatableResponse res =  RestAssured.given().param("q", "Mumbaii").param("appid", "84dea09827b5761f173a4204352c2a19").when().get().then();
		res.statusCode(404);
		res.body("message", Matchers.equalToIgnoringCase("city not found"));
		Reporter.log("Error 404 message status verified successfully", true);
		

		
		
		
	}



}

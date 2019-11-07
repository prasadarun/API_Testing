package com.Test.apiTest;

import org.hamcrest.Matchers;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Demo.apiTesting.DemoExcelReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class DataProviderWeatherAPI {


	public static String path = "/Users/durga/eclipse-workspace/API_Selenium_Testing/Test_Data.xl/API_Testing_Doct.xlsx";
	public static String Sheetname = "PositiveTestCaseData";
	public static String Sheetname2 = "NegativeTestCaseData";



	// To Print the Data present in Excel Sheet for Demo purpose
	/*@Test(dataProvider="TestData")
	public void test(String Test_Case_Name,String CityName,String App_ID,String Status_Code,String Country_Code ) {

	System.out.println(Test_Case_Name+" |" +CityName+" |" +App_ID+" |" +Status_Code+" |" + Country_Code);

	}

	@Test(dataProvider="TestData2")
	public void test2(String Test_Case_Name,String CityName,String App_ID,String Status_Code,String Country_Code,String ErrorMessage ) {

	System.out.println(Test_Case_Name+" |" +CityName+" |" +App_ID+" |" +Status_Code+" |" + Country_Code+" |"+ErrorMessage );

	}*/


	@Test(priority=1,dataProvider="PositiveTestCases")
	public void getWeatherInfoWithPositiveTestCases(String Test_Case_Name,String CityName,String App_ID,String Status_Code,String Country_Code) {
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/weather";
		ValidatableResponse response =  RestAssured.given().param("q", CityName).param("appid",App_ID ).when().get().then();
		System.out.println("Response is :- " + response);

		Reporter.log("Test_Case_Name is : "+Test_Case_Name,true);

		Reporter.log("Response is : "+response.extract().asString(),true);

		int StsCode = Integer.parseInt(Status_Code);
		response.statusCode(StsCode);
		Reporter.log("Verified the status code successfully",true);


		response.contentType(ContentType.JSON);
		response.body("sys.country", Matchers.notNullValue());
		response.body("sys.country", Matchers.equalToIgnoringCase(Country_Code));
		Reporter.log("Country Code has Verified Sucessfully",true);


		response.contentType(ContentType.JSON);
		response.body("name", Matchers.notNullValue());
		response.body("name", Matchers.equalToIgnoringCase(CityName));
		Reporter.log("CityName has Verified Sucessfully",true);


	}


	@Test(priority=2,dataProvider="NegativeTestCases")
	public void getWeatherInfoWithNegativeTestCases(String Test_Case_Name,String CityName,String App_ID,String Status_Code,String Country_Code,String ErrorMessages ) {
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/weather";
		ValidatableResponse res =  RestAssured.given().param("q", CityName).param("appid",App_ID ).when().get().then();

		Reporter.log("Response is: "+res.extract().asString(), true);
		Reporter.log("Test_Case_Name is : "+Test_Case_Name,true);

		int StsCode = Integer.parseInt(Status_Code);
		res.statusCode(StsCode);


		res.body("message",Matchers.notNullValue());
		res.body("message",Matchers.containsString(ErrorMessages));
		Reporter.log("Verified the error Messages Sucessfully", true);

	}



	// Data Provider for Positive Test Cases Data
	@DataProvider(name = "PositiveTestCases")
	public static Object testData1() {
		Object data = getData(path, Sheetname);
		return data;

	}

	// Data Provider for Negative Test Cases Data
	@DataProvider(name="NegativeTestCases")
	public static Object testData2() {
		Object test2 = getData(path, Sheetname2);
		return test2;

	}



	// Getting the CellData form
	public static Object[][] getData(String path, String Sheetname) {
		DemoExcelReader excel = new DemoExcelReader(path,Sheetname);
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object data[][]= new Object[rowCount-1][colCount];

		for (int i=1;i<rowCount;i++) {
			for (int j=0;j<colCount;j++) {

				String celldata = excel.getcellData(i, j);
				//System.out.print(celldata+" |");
				data[i-1][j]=celldata;

			}

			//System.out.println();
		}

		return data;

	}

	/*public static void main(String args[]) {
		getData(path, Sheetname);

	}*/



}

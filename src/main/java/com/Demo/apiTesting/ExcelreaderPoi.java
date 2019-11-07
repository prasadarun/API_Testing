package com.Demo.apiTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class ExcelreaderPoi {

	
static String exceldata;
static int ColumnCount=0;
static int RowCount=0;
static Object celldata[][];


	
	@Test(dataProvider="testData")
	public void gettingdata(String City_Name,String App_ID,String Status_Code,String Country_Code) {
		
		System.out.print(City_Name+ App_ID+Status_Code+Country_Code);
		
	}
	
	
	
	@DataProvider(name="testData")

	public void ExcelIntilization(String ExcelSheetName) throws IOException {

		FileInputStream file = new FileInputStream("/Users/durga/eclipse-workspace/API_Selenium_Testing/Test_Data.xl/API_Testing_Doct.xlsx");

		XSSFWorkbook  Workbook = new XSSFWorkbook(file);
		int countOfSheets = Workbook.getNumberOfSheets();
	

		for(int i =0; i<countOfSheets; i++) {

			if(Workbook.getSheetName(i).equalsIgnoreCase("PositiveTestCaseData")) {

				XSSFSheet sheetname = Workbook.getSheetAt(i);
				XSSFRow rowheader = sheetname.getRow(0);

				int ColumnCount = rowheader.getLastCellNum();

				System.out.println("Excel sheet Column Count is ==> "+ColumnCount);

				int RowCount = sheetname.getLastRowNum();

				System.out.println("Excel sheet Row Count is ==> "+RowCount);
				
//				Object celldata[][] = new Object[RowCount-1][ColumnCount];


				for (int x = 0; x < RowCount; x++) {

					Row rowdata = sheetname.getRow(x);

					for (int y=0 ; y< ColumnCount; y++) {

						//System.out.print(rowdata.getCell(y).getStringCellValue()+"|| ");
						//exceldata.add(rowdata.getCell(y).getStringCellValue());
						
						exceldata = rowdata.getCell(y).getStringCellValue();						
					}
					
					System.out.println();
				}
			}
		}
	
		System.out.println(exceldata);
		//return celldata;


	}

}











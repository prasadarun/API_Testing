package com.Demo.apiTesting;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DemoExcelReader {
	
	
	private static final String Pojectpath = null;
	FileInputStream file;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static int rowCount;

	
	
	// Constructor to load the Project file path with sheetname
	public DemoExcelReader(String path,String Sheetname) {
		//String Projectpath = System.getProperty("user.dir");
		try {
			FileInputStream file = new FileInputStream(path);
			workbook = new XSSFWorkbook(file);
			int  index = workbook.getSheetIndex(Sheetname);
			sheet = workbook.getSheetAt(index);
			
		}
		catch(Exception e) {
			e.printStackTrace();

		}

	}
	
	// Getting the no of RowsCount
	
	public int getRowCount() {
		int rowCount =0;
		try {
			
			rowCount = sheet.getPhysicalNumberOfRows();
			//System.out.print("No of rows is "+rowCount);
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return rowCount;
	}
	
	// Getting the no of ColumnCount
	
	public  int getColCount() {
		int colCount=0;
		try {
			
			colCount = sheet.getRow(0).getLastCellNum();
			//System.out.print("No of Column is "+rowCount);
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return colCount;
	}
	
	// Getting the CellData with RowCount and Column Count

	public String getcellData(int rowCount, int colCount) {    
		String celldata = null ;
		try {
			 celldata = sheet.getRow(rowCount).getCell(colCount).toString();
			//System.out.print(celldata);
		}

		catch(Exception e) {
			e.printStackTrace();

		}
		return celldata;
	}
	
	

}

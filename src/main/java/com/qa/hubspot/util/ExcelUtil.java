package com.qa.hubspot.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static Workbook book;
	private static Sheet sheet;

	private static String TEST_DATA_SHEET_PATH = "./src/main/java/com/qa/hubspot/testdata/TestData.xlsx";

	/**
	 * @param sheetName
	 * @return
	 */
	public static Object[][] getTestData(String sheetName) {
		Object[][] data = null;

		FileInputStream fis;
		try {
			fis = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);

//			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
//			for (int i = 0; i < sheet.getLastRowNum(); i++) {
//				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
//					data[i][j] = sheet.getRow(i).getCell(j).toString();
//				}
//			}

			data = new Object[sheet.getLastRowNum()][1];
			Hashtable<String, String> table = null;
			for(int i=0;i<sheet.getLastRowNum();i++) {
				table=new Hashtable<String, String>();
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					table.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
					data[i][0]=table;
				}
				System.out.println(table);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}
}

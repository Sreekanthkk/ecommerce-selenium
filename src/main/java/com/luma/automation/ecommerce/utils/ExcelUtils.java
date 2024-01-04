package com.luma.automation.ecommerce.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	public static Object[][] getData(String filePath,String worksheet) {
		
		try {
			FileInputStream fis=new FileInputStream(filePath);
			Workbook workbook=WorkbookFactory.create(fis);
			Sheet sheet=workbook.getSheet(worksheet);
			int rowCount=sheet.getLastRowNum();
			int columnCount=sheet.getRow(0).getLastCellNum();
			Object[][] data=new Object[rowCount][columnCount];
			for(int i=1;i<rowCount+1;i++) {
				for(int j=0;j<columnCount;j++) {
					data[i-1][j]=sheet.getRow(i).getCell(j).toString();
				}
			}
			return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

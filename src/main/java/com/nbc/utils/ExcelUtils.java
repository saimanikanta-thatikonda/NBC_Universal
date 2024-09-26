package com.nbc.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class ExcelUtils {
    private ExcelUtils()
    {

    }
    public static String[][] readExcel(String filePath, String sheetName) {
        String[][] data = null;
        // Create a Workbook object
        Workbook workbook = null;
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath))) {
            workbook = new XSSFWorkbook(fileInputStream);
            // Get the sheet as per name
            Sheet sheet = workbook.getSheet(sheetName);
            int rows=sheet.getLastRowNum();
            int columns=sheet.getRow(0).getLastCellNum();
            data = new String[rows][columns];
            // Iterate through the rows
            for (int i=1;i<=rows;i++) {
                // Iterate through the columns in the current row
                for (int j=0;j<columns;j++) {
                    data[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

}

package com.flipkart.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    //initialise constructor
    public ExcelUtils(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet with name " + sheetName + " doesn't exist in file " + filePath);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get cell data
    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return null;
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return null;
        }
        return cell.toString();
    }

    //get number of rows
    public int getRowCount() {
        return sheet.getLastRowNum() + 1; // Because rows are 0-indexed
    }

    //close workbook
    public void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

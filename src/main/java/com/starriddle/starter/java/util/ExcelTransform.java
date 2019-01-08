package com.starriddle.starter.java.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * description
 *
 * @author CYL
 * @date 2019-01-08
 */
public class ExcelTransform {
    public static void main(String[] args) throws InvalidFormatException, IOException {
        File file = new File(ExcelTransform.class.getResource("/download.xlsx").getPath());
        InputStream is = new FileInputStream(file);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input2byte(is));

        Workbook wb = WorkbookFactory.create(inputStream);
        for (int sheetNo=0; sheetNo<wb.getNumberOfSheets(); sheetNo++) {
            Sheet sheet = wb.getSheetAt(sheetNo);
            System.out.println(sheet.getSheetName());
            for (int rowNo=sheet.getFirstRowNum(); rowNo<=sheet.getLastRowNum(); rowNo++) {
                Row row = sheet.getRow(rowNo);
                StringBuilder sb = new StringBuilder();
                if (row.getPhysicalNumberOfCells()==0) {
                    break;
                }
                if (rowNo==0) {
                    for (int cellNo = 0; cellNo < 5; cellNo++) {
                        Cell cell = row.getCell(cellNo);
                        if (cell == null) {
                            continue;
                        }
                        sb.append(" "+cell.getStringCellValue());
                    }
                    System.out.println(sb.substring(1));
                    continue;
                }
                for (int cellNo = 0; cellNo < 5; cellNo++) {
                    Cell cell = row.getCell(cellNo);
                    if (cell == null) {
                        continue;
                    }
                    String value = null;
                    switch (cellNo) {
                        case 0:
                        case 1:
                        case 4:
                            if (cell.getCellType()==Cell.CELL_TYPE_BLANK) {
                                value = "[无内容]";
                            } else if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
                                value = new BigDecimal(String.valueOf(cell.getNumericCellValue())).setScale(0).toString();
                            } else {
                                value = cell.getStringCellValue();
                            }
                            break;
                        case 2:
                            if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
                                value = "[无金额]";
                            } else {
                                value = new BigDecimal(String.valueOf(cell.getNumericCellValue())).setScale(2).toString();
                            }
                            break;
                        case 3:
                            if (cell.getCellType()==Cell.CELL_TYPE_BLANK){
                                value = "[无日期]";
                            } else {
                                value = cell.getDateCellValue().toString();
                            }
                            break;
                            default:
                    }
                    sb.append(" "+value);
                }
                System.out.println(sb.substring(1));
            }
        }
    }

    public static final byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
}

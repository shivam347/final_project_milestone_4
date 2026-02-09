package com.milestone.four.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.milestone.four.model.TestResultData;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelReportUtil {

       public static void writeFinalReport(List<TestResultData> results) {

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Automation Report");

            // Header
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Test Case");
            header.createCell(1).setCellValue("Status");
            header.createCell(2).setCellValue("Execution Time");

            int rowNum = 1;

            // Data
            for (TestResultData data : results) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(data.testName);
                row.createCell(1).setCellValue(data.status);
                row.createCell(2).setCellValue(data.time);
            }

            FileOutputStream fos =
                new FileOutputStream("Final_Test_Report.xlsx");

            workbook.write(fos);

            workbook.close();
            fos.close();

            System.out.println("Final Excel Report Created");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

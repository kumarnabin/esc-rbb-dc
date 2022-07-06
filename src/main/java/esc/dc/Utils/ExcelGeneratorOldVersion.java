package esc.dc.Utils;


import esc.dc.Model.OldChalani;
import esc.dc.Model.OldDarta;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ExcelGeneratorOldVersion {

    public static ByteArrayInputStream dartaToExcel(List<OldDarta> dartas) throws IOException {
        String[] COLUMNs = {"दर्ता नं", "दर्ता मिति", "(साल)द नं","पत्रको मिति","बिषय", "कार्यालय/Person","ठेगाना","फाँट"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("dartaOld");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (OldDarta darta : dartas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(darta.getDartaNumber());
                row.createCell(1).setCellValue(darta.getDartaDate());
                row.createCell(2).setCellValue(darta.getSaal());
                row.createCell(3).setCellValue(darta.getLetterDate());
                row.createCell(4).setCellValue(darta.getSubject());
                row.createCell(5).setCellValue(darta.getOfficePerson());
                row.createCell(6).setCellValue(darta.getAddress());
                row.createCell(7).setCellValue(darta.getFaat());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static ByteArrayInputStream chalaniToExcel(List<OldChalani> chalanis) throws Exception{
        String[] COLUMNs = {"चलानि नं", "चलानि मिति", "(साल)द नं","पत्रको मिति","बिषय", "कार्यालय/Person","ठेगाना","फाँट"};
        try (
                Workbook workbook2 = new XSSFWorkbook();
                ByteArrayOutputStream out2 = new ByteArrayOutputStream();) {
            CreationHelper createHelper = workbook2.getCreationHelper();

            Sheet sheet = workbook2.createSheet("chalani");

            Font headerFont = workbook2.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook2.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook2.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (OldChalani chalani : chalanis) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(chalani.getChalaniNumber());
                row.createCell(1).setCellValue(chalani.getChalaniDate());
                row.createCell(2).setCellValue(chalani.getSaal());
                row.createCell(3).setCellValue(chalani.getLetterDate());
                row.createCell(4).setCellValue(chalani.getSubject());
                row.createCell(5).setCellValue(chalani.getOfficePerson());
                row.createCell(6).setCellValue(chalani.getAddress());
                row.createCell(7).setCellValue(chalani.getFaat());
            }

            workbook2.write(out2);
            return new ByteArrayInputStream(out2.toByteArray());
        }
    }
}

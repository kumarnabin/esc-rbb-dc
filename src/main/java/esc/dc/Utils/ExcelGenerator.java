package esc.dc.Utils;

import esc.dc.Model.Chalani;
import esc.dc.Model.Darta;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ExcelGenerator {

    public static ByteArrayInputStream dartaToExcel(List<Darta> dartas) throws IOException {
        String[] COLUMNs = {"दर्ता नं", "दर्ता मिति", "(साल)द नं","पत्रको मिति","पत्रको च. नं.","बिषय","प्रकार", "कार्यालय/Person","ठेगाना","contact","फाँट"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("darta");

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
            for (Darta darta : dartas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(darta.getDartaNumber());
                row.createCell(1).setCellValue(darta.getDartaDate());
                row.createCell(2).setCellValue(darta.getPraSamWaChaNo());
                row.createCell(3).setCellValue(darta.getLetterDate());
                row.createCell(4).setCellValue(darta.getSignature());
                row.createCell(5).setCellValue(darta.getSubject());


                row.createCell(6).setCellValue(darta.getFormattedDartaType());

                if(darta.recivType){
                    row.createCell(7).setCellValue(darta.getDartaPerson().getFirstName() + ' '+darta.getDartaPerson().getMiddleName() + ' '+darta.getDartaPerson().getLastName() );
                    row.createCell(8).setCellValue(darta.getDartaPerson().getAddress());
                    row.createCell(9).setCellValue(darta.getDartaPerson().getContactNo());
                }else{
                    row.createCell(7).setCellValue(darta.getOffice().getName());
                    row.createCell(8).setCellValue(darta.getOffice().getLocation());
                    row.createCell(9).setCellValue(darta.getOffice().getContactNo());
                }

                row.createCell(10).setCellValue(darta.getFaat());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static ByteArrayInputStream chalaniToExcel(List<Chalani> chalanis) throws Exception{
        String[] COLUMNs = {"Chalani No", "Chalani मिति", "(साल)","पत्रको मिति","बिषय","प्रकार", "कार्यालय/Person","ठेगाना","contact","फाँट"};
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
            for (Chalani chalani : chalanis) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(chalani.getChalaniNumber());
                row.createCell(1).setCellValue(chalani.getChalaniDate());
                row.createCell(2).setCellValue(chalani.getPraSamWaChaNo());
                row.createCell(3).setCellValue(chalani.getLetterDate());
                row.createCell(4).setCellValue(chalani.getSubject());


                row.createCell(5).setCellValue(chalani.getFormattedDartaType());

                if(chalani.recivType==true){
                    row.createCell(6).setCellValue(chalani.getChalaniPerson().getFirstName() + ' '+chalani.getChalaniPerson().getMiddleName() + ' '+chalani.getChalaniPerson().getLastName() );
                    row.createCell(7).setCellValue(chalani.getChalaniPerson().getAddress());
                    row.createCell(8).setCellValue(chalani.getChalaniPerson().getContactNo());
                }else{
                    row.createCell(6).setCellValue(chalani.getOffice().getName());
                    row.createCell(7).setCellValue(chalani.getOffice().getLocation());
                    row.createCell(8).setCellValue(chalani.getOffice().getContactNo());
                }

                row.createCell(9).setCellValue(chalani.getFaat());
            }

            workbook2.write(out2);
            return new ByteArrayInputStream(out2.toByteArray());
        }
    }
}

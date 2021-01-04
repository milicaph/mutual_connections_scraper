package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import properties.Parameters;

import java.io.*;
import java.util.ArrayList;


public class ReadWriteExcel {
    private static FileInputStream getInputStream() throws IOException {
        return new FileInputStream(Parameters.excelFileLocation);

    }

    private static FileOutputStream getOutputStream() throws IOException {
        return new FileOutputStream(Parameters.excelFileLocation);

    }

    private static Workbook getWorkbook(FileInputStream fis) throws IOException {
        return new XSSFWorkbook(fis);

    }

    private static Sheet getSheet(Workbook workbook, String inputOrOutput) {
        return workbook.getSheet(inputOrOutput);

    }

    private static void writeDataOutput(Workbook workbook){
        try {
            workbook.write(getOutputStream());
        } catch (IndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean cellIsEmpty(Cell c){
        return c == null || c.getCellType() == CellType.BLANK;
    }

    private static Row exactRow(Sheet sheet, String friendUrl){
        for(Row row: sheet) {
            Cell cName = row.getCell(2);
            if(!cellIsEmpty(cName)) {
                boolean containsSring = cName.getStringCellValue()
                        .contains(friendUrl);
                if (containsSring)
                    return row;
            }
        }
        return null;

    }

    public static void fillInArrayListsFromExcel(ArrayList<String> name, ArrayList<String> lastName, ArrayList<String> url) throws IOException {
        FileInputStream fis = getInputStream();
        Workbook workbook = getWorkbook(fis);
        Sheet sheetI = getSheet(workbook, "input");

        for(Row row: sheetI) {
            Cell cName = row.getCell(0);
            System.out.println(cName.getStringCellValue());
            Cell cSurname = row.getCell(1);
            System.out.println(cSurname.getStringCellValue());
            Cell cURL = row.getCell(2);
            Cell cDate = row.getCell(3);

            try {
                if (cellIsEmpty(cURL)) {
                    continue;
                } else if (cellIsEmpty(cDate)){
                    name.add(cName.getStringCellValue());
                    lastName.add(cSurname.getStringCellValue());
                    url.add(cURL.getStringCellValue());

                }

        } catch (NullPointerException e) { e.printStackTrace(); }

        }

    }
    public static void writeData(String friendName, String friendLastName, String friendUrl,
                                 ArrayList<String> connectionNames, ArrayList<String> connectionUrls) throws IOException {

        FileInputStream fis = getInputStream();
        Workbook workbook = getWorkbook(fis);
        Sheet sheetO = getSheet(workbook, "output");
        Sheet sheetI = getSheet(workbook, "input");

        for(String connectionName : connectionNames) {
            int i = sheetO.getLastRowNum() + 1;
            System.out.println("ROWNUM " + i);
            Row row = sheetO.createRow(i);

            int indexOf = connectionNames.indexOf(connectionName);
            Cell cNamesFriends = row.createCell(0);
            cNamesFriends.setCellValue(friendName + " " + friendLastName);
            System.out.println("LinekedIn Friend Name "+ friendName + " " + friendLastName);
            Cell cConnectionNames = row.createCell(1);
            cConnectionNames.setCellValue(connectionName);
            System.out.println("Connection Name "+ connectionName);
            Cell cConnectionURLs = row.createCell(2);
            cConnectionURLs.setCellValue(connectionUrls.get(indexOf));
            System.out.println("Connection URL "+ connectionUrls.get(indexOf));
            Cell cDate = row.createCell((3));
            String date = SelUtils.getCurrentDate();
            cDate.setCellValue(date);

            Row rowI = exactRow(sheetI, friendUrl);
            Cell cDateInput = rowI.getCell(3);
            if(cellIsEmpty(cDateInput)) {
                cDateInput = rowI.createCell(3);
                cDateInput.setCellValue(date);
                writeDataOutput(workbook);
            }

            writeDataOutput(workbook);


        }

    }

    public static void writeDateToInputNoConnections(String url) throws IOException {
        FileInputStream fis = getInputStream();
        Workbook workbook = getWorkbook(fis);
        Sheet sheetI = getSheet(workbook, "input");
        Row row = exactRow(sheetI, url);
        Cell cDate = row.createCell(3);
        cDate.setCellValue(SelUtils.getCurrentDate());
        writeDataOutput(workbook);

    }

}

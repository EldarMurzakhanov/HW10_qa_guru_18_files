package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadingAndParsingZip {

    private ClassLoader cl = ReadingAndParsingZip.class.getClassLoader();

    @Test
    @DisplayName("Чтение PDF из архива zip")
    void testPDF() throws Exception {

        try (InputStream is = cl.getResourceAsStream("HomeWork.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("HWpdf.pdf")) {
                    PDF pdf = new PDF(zs);
                    Assertions.assertTrue(entry.getName().contains("HWpdf.pdf"));
                    Assertions.assertEquals("Glushakova, Anastasiya", pdf.author);
                }
            }
        }
    }
    @Test
    @DisplayName("Чтение XLS из архива zip")
    void testXLS() throws Exception {

        try (InputStream is = cl.getResourceAsStream("HomeWork.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("HWxlsx.xlsx")) {
                    XLS xls = new XLS(zs);
                    Assertions.assertTrue(entry.getName().contains("HWxlsx.xlsx"));
                    Assertions.assertTrue(xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue().startsWith("Single Spin"));
                }
            }
        }
    }
    @Test
    @DisplayName("Чтение CSV из архива zip")
    void testCSV() throws Exception {

        try (InputStream is = cl.getResourceAsStream("HomeWork.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("HWcsv.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zs));
                    List<String[]> csvRow = csvReader.readAll();
                    Assertions.assertTrue(entry.getName().contains("HWcsv.csv"));
                    Assertions.assertArrayEquals(new String[] {"Route No.","Trip No.","Call No.","Nearest Win Day",
                            "Order Number","Name","Mobile Phone Number","Telephone 2","Address","Postcode","Volume","Cabinets",
                            "AM/PM Slot","Delivery Window","Arrival Time","Call Duration","","Departure Time","Trip Start","Trip End",
                            "Call Type","Depot Name","Duty Time","Customer ID","Load ID","Run Number","vehicletype"}, csvRow.get(0));
                }
            }
        }
    }
}
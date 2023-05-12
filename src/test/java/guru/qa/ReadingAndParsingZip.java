package guru.qa;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadingAndParsingZip {

    private ClassLoader cl = ReadingAndParsingZip.class.getClassLoader();

    @Test
    @DisplayName("Чтение PDF из архива zip")
    void testPDF() throws Exception {
        try (InputStream is = cl.getResourceAsStream("HW10.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("ДЗ_DevTools_Воркшоп_1.pdf")) {
                    PDF pdf = new PDF(zs);
                    System.out.println();

                    // Assertions.assertTrue(entry.getName().contains("ДЗ_DevTools_Воркшоп_1.pdf"));
                }
            }
        }
    }
}
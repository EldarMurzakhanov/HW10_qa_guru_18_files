package guru.qa;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParsingTest {

    private ClassLoader cl = ReadingAndParsingZip.class.getClassLoader();

    @Test
    @DisplayName("Парсинг JSON")
    void testJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = cl.getResourceAsStream("HomeWork.json");
             InputStreamReader isr = new InputStreamReader(is)) {
            JsonObject jsonObject = mapper.readValue(isr, JsonObject.class);

                    Assertions.assertTrue(jsonObject.retro);
                    Assertions.assertEquals(1992, jsonObject.release);
                }
            }
        }
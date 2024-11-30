package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> readFromFile(String fileName, Class<T> clazz) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return null;
            }
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static <T> void writeToFile(String fileName, List<T> list) {
        try {
            objectMapper.writeValue(new File(fileName), list);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

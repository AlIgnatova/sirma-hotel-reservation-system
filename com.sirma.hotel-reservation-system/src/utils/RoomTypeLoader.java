package utils;


import models.RoomType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeLoader {
//
//    // Method to load room types from a JSON-like text file
//    public static List<RoomType> loadRoomTypes(String filePath) throws IOException {
//        List<RoomType> roomTypes = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            StringBuilder fileContent = new StringBuilder();
//
//            while ((line = reader.readLine()) != null) {
//                fileContent.append(line.trim());
//            }
//
//            String content = fileContent.toString();
//
//            // Remove the square brackets and extra whitespace
//            content = content.substring(1, content.length() - 1).trim();
//
//            // Split each room type block by '}, {' to process them separately
//            String[] roomTypeBlocks = content.split("},\\{");
//
//            for (String roomBlock : roomTypeBlocks) {
//                // Remove any excess curly braces
//                roomBlock = roomBlock.replace("{", "").replace("}", "").trim();
//
//                // Split by commas to get the individual fields
//                String[] fields = roomBlock.split(",");
//
//                String name = "";
//                int maxOccupancy = 0;
//                List<String> amenities = new ArrayList<>();
//
//                for (String field : fields) {
//                    field = field.trim();
//
//                    // Parse the name
//                    if (field.startsWith("\"type\":")) {
//                        name = field.split(":")[1].trim().replace("\"", "");
//                    }
//
//                    // Parse the maxOccupancy
//                    if (field.startsWith("\"maxOccupancy\":")) {
//                        maxOccupancy = Integer.parseInt(field.split(":")[1].trim());
//                    }
//
//                    // Parse amenities
//                    if (field.startsWith("\"amenities\":")) {
//                        String amenitiesString = field.split(":")[1].trim();
//                        amenitiesString = amenitiesString.substring(1, amenitiesString.length() - 1); // Remove the brackets
//                        String[] amenityArray = amenitiesString.split(",");
//                        for (String amenity : amenityArray) {
//                            amenities.add(amenity.trim().replace("\"", ""));
//                        }
//                    }
//                }
//
//                // Create a new RoomType object and add it to the list
//                RoomType roomType = new RoomType(name, maxOccupancy, amenities);
//                roomTypes.add(roomType);
//            }
//
//        } catch (IOException e) {
//            System.err.println("Error reading the room types file: " + e.getMessage());
//        }
//
//        return roomTypes;
//    }

    // Method to load room types from a JSON-like text file
    public static List<RoomType> loadRoomTypes(String filePath) {
        List<RoomType> roomTypes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                fileContent.append(line.trim());
            }

            String content = fileContent.toString();

            // Ensure that the content starts and ends with square brackets
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1).trim(); // Remove the outer square brackets
            }

            // Split the content by '},{' to get each room type block
            String[] roomTypeBlocks = content.split("},\\s*\\{");

            for (String roomBlock : roomTypeBlocks) {
                roomBlock = roomBlock.replace("{", "").replace("}", "").trim(); // Clean up curly braces

                String name = "";
                int maxOccupancy = 0;
                List<String> amenities = new ArrayList<>();

                // Split the block into fields by commas, respecting the structure of JSON
                String[] fields = roomBlock.split("\",?\\s*\"");

                for (String field : fields) {
                    field = field.trim();
                    if (field.contains("\"name\":")) {
                        name = extractFieldValue(field, "name");
                    } else if (field.contains("\"maxOccupancy\":")) {
                        maxOccupancy = Integer.parseInt(extractFieldValue(field, "maxOccupancy"));
                    } else if (field.contains("\"amenities\":")) {
                        String amenitiesString = extractFieldValue(field, "amenities");
                        amenities = parseAmenities(amenitiesString);
                    }
                }

                // Create a new RoomType object and add it to the list
                RoomType roomType = new RoomType(name, maxOccupancy, amenities);
                roomTypes.add(roomType);
            }

        } catch (IOException e) {
            System.err.println("Error reading the room types file: " + e.getMessage());
        }

        return roomTypes;
    }

    // Helper method to extract a field value from a string
    private static String extractFieldValue(String field, String key) {
        String[] parts = field.split(":");
        String value = parts[1].trim();
        return value.replace("\"", "").trim();  // Remove any quotes around the value
    }

    // Parse the amenities field and return a list of amenities
    private static List<String> parseAmenities(String amenitiesString) {
        List<String> amenities = new ArrayList<>();

        // Remove the square brackets from the string (e.g., ["Wi-Fi", "TV"])
        amenitiesString = amenitiesString.substring(1, amenitiesString.length() - 1);

        // Split the string by commas to extract individual amenities
        String[] amenitiesArray = amenitiesString.split(",");

        // Clean up each amenity and add to the list
        for (String amenity : amenitiesArray) {
            amenities.add(amenity.trim().replace("\"", ""));
        }
        return amenities;
    }
}
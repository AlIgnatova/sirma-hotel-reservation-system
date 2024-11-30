package utils;


import models.RoomType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeLoader {

    // Method to load room types from a JSON-like text file
    public static List<RoomType> loadRoomTypes(String filePath) throws IOException {
        List<RoomType> roomTypes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                fileContent.append(line.trim());
            }

            String content = fileContent.toString();

            // Remove the square brackets and extra whitespace
            content = content.substring(1, content.length() - 1).trim();

            // Split each room type block by '}, {' to process them separately
            String[] roomTypeBlocks = content.split("},\\{");

            for (String roomBlock : roomTypeBlocks) {
                // Remove any excess curly braces
                roomBlock = roomBlock.replace("{", "").replace("}", "").trim();

                // Split by commas to get the individual fields
                String[] fields = roomBlock.split(",");

                String name = "";
                int maxOccupancy = 0;
                List<String> amenities = new ArrayList<>();

                for (String field : fields) {
                    field = field.trim();

                    // Parse the name
                    if (field.startsWith("\"name\":")) {
                        name = field.split(":")[1].trim().replace("\"", "");
                    }

                    // Parse the maxOccupancy
                    if (field.startsWith("\"maxOccupancy\":")) {
                        maxOccupancy = Integer.parseInt(field.split(":")[1].trim());
                    }

                    // Parse amenities
                    if (field.startsWith("\"amenities\":")) {
                        String currentAmenities = roomBlock.split("\"amenities\":")[1];
                        //String amenitiesString = field.split(":")[1].trim();
                        //currentAmenities = currentAmenities.substring(1, currentAmenities.length() -2 ); // Remove the brackets
                        currentAmenities = currentAmenities.replaceAll("\\[","");
                        String[] amenityArray = currentAmenities.split(",");
                        for (String amenity : amenityArray) {
                            amenities.add(amenity.trim().replace("\"", ""));
                        }
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
//    }

//    public static List<RoomType> loadRoomTypes(String filePath) {
//        List<RoomType> roomTypes = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            StringBuilder jsonContent = new StringBuilder();
//            String line;
//            while ((line = br.readLine()) != null) {
//                jsonContent.append(line);
//            }
//
//
//            String content = jsonContent.toString();
//            content = content.substring(1, content.length() - 1); // Remove outer square brackets
//            String[] roomTypeBlocks = content.split("},\\{");
//
//            for (String roomBlock : roomTypeBlocks) {
//                roomBlock = roomBlock.replace("{", "").replace("}", "").trim();
//                String[] fields = roomBlock.split(",");
//
//                String name = "";
//                int maxOccupancy = 0;
//                List<String> amenities = new ArrayList<>();
//
//                for (String field : fields) {
//                    field = field.trim();
//
//                    if (field.startsWith("\"name\":")) {
//                        name = field.split(":")[1].trim().replace("\"", "");
//                    }
//
//                    if (field.startsWith("\"maxOccupancy\":")) {
//                        maxOccupancy = Integer.parseInt(field.split(":")[1].trim());
//                    }
//
//                    if (field.startsWith("\"amenities\":")) {
//                        String amenitiesString = field.split(":")[1].trim();
//                        amenitiesString = amenitiesString.substring(1, amenitiesString.length() - 1); // Remove brackets
//                        String[] amenityArray = amenitiesString.split(",");
//                        for (String amenity : amenityArray) {
//                            amenities.add(amenity.trim().replace("\"", ""));
//                        }
//                    }
//                }
//
//                roomTypes.add(new RoomType(name, maxOccupancy, amenities));
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading room types file: " + e.getMessage());
//        }
//
//        return roomTypes;
    }
}
package utils;


import models.RoomType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeLoader {


    public static List<RoomType> loadRoomTypes(String filePath) {
        List<RoomType> roomTypes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                fileContent.append(line.trim());
            }

            String content = fileContent.toString();


            content = content.substring(1, content.length() - 1).trim();


            String[] roomTypeBlocks = content.split("},\\{");

            for (String roomBlock : roomTypeBlocks) {

                roomBlock = roomBlock.replace("{", "").replace("}", "").trim();


                String[] fields = roomBlock.split(",");

                String name = "";
                int maxOccupancy = 0;
                List<String> amenities = new ArrayList<>();

                for (String field : fields) {
                    field = field.trim();


                    if (field.startsWith("\"name\":")) {
                        name = field.split(":")[1].trim().replace("\"", "");
                    }


                    if (field.startsWith("\"maxOccupancy\":")) {
                        maxOccupancy = Integer.parseInt(field.split(":")[1].trim());
                    }


                    if (field.startsWith("\"amenities\":")) {
                        String currentAmenities = roomBlock.split("\"amenities\":")[1];

                        currentAmenities = currentAmenities.replaceAll("\\[","");
                        String[] amenityArray = currentAmenities.split(",");
                        for (String amenity : amenityArray) {
                            amenities.add(amenity.trim().replace("\"", ""));
                        }
                    }
                }

                RoomType roomType = new RoomType(name, maxOccupancy, amenities);
                roomTypes.add(roomType);
            }

        } catch (IOException e) {
            System.err.println("Error reading the room types file: " + e.getMessage());
        }

        return roomTypes;

    }
}
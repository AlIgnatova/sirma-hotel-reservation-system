package models;

import java.util.List;

public class RoomType {

    private String typeName;
    private int maxOccupancy;
    private List<String> amenities;

    public RoomType(String typeName, int maxOccupancy, List<String> amenities) {
        this.typeName = typeName;
        this.maxOccupancy = maxOccupancy;
        this.amenities = amenities;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public int getMaxOccupancy() {
        return this.maxOccupancy;
    }

    public List<String> getAmenities() {
        return this.amenities;
    }

    public void printRoomTypeDetails() {
        System.out.println("Room Type: " + this.typeName);
        System.out.println("Max Occupancy: " + this.maxOccupancy);
        System.out.println("Amenities: " + this.amenities);
    }

}

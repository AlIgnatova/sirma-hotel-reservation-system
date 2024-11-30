package models;

public class Room {
    private String roomNumber;
    private RoomType roomType;
    private double pricePerNight;
    private double cancellationFee;
    private String status;

    public Room(String roomNumber, RoomType roomType, double pricePerNight, double cancellationFee, String status) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.cancellationFee = cancellationFee;
        this.status = status;
    }


    public String getRoomNumber() {
        return this.roomNumber;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public double getPricePerNight() {
        return this.pricePerNight;
    }

    public double getCancellationFee() {
        return this.cancellationFee;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void printRoomDetails() {
        System.out.println("Room Number: " + this.roomNumber);
        System.out.println("Type: " + this.roomType.getTypeName());
        System.out.println("Price per Night: $" + this.pricePerNight);
        System.out.println("Cancellation Fee: $" + this.cancellationFee);
        System.out.println("Status: " + this.status);
    }
}

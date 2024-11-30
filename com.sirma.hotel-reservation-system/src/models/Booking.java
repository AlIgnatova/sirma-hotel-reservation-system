package models;

public class Booking {
    private String reservationId;
    private String username;
    private String roomNumber;
    private String startDate;
    private String endDate;
    private double totalAmount;

    public Booking(String reservationId, String username, String roomNumber, String startDate, String endDate, double totalAmount) {
        this.reservationId = reservationId;
        this.username = username;
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
    }


    public String getReservationId() {
        return this.reservationId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public void printBookingDetails() {
        System.out.println("Reservation ID: " + this.reservationId);
        System.out.println("Username: " + this.username);
        System.out.println("Room Number: " + this.roomNumber);
        System.out.println("Start Date: " + this.startDate);
        System.out.println("End Date: " + this.endDate);
        System.out.println("Total Amount: $" + this.totalAmount);
    }

}

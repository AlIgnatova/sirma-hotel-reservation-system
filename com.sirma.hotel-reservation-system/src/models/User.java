package models;


import java.util.List;

public class User {
    private String username;
    private String password;
    private List<String> bookingHistory;

    public User(String username, String password, List<String> bookingHistory) {
        this.username = username;
        this.password = password;
        this.bookingHistory = bookingHistory;
    }


    public String getUsername() {
        return this.username;
    }

    private String getPassword() {
        return this.password;
    }

    public List<String> getBookingHistory() {
        return this.bookingHistory;
    }

    public void addBookingToHistory(String bookingDetails) {
        this.bookingHistory.add(bookingDetails);
    }

    public void printUserProfile() {
        System.out.println("Username: " + this.username);
        System.out.println("Booking History: " + this.bookingHistory);
    }
}

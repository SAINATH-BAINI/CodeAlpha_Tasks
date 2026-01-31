// Hotel Reservation System (Console Based)
// Uses OOP concepts + File I/O 
//Data is stored using serialization,instead of a database

import java.io.*;
import java.util.*;

// ---------- Room Class ----------
class Room implements Serializable {
    int roomId;
    String category; // Standard, Deluxe, Suite
    double price;
    boolean isAvailable;

    Room(int roomId, String category, double price) {
        this.roomId = roomId;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }
}

// ---------- Booking Class ----------
class Booking implements Serializable {
    int bookingId;
    String customerName;
    Room room;
    String paymentStatus;

    Booking(int bookingId, String customerName, Room room) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.room = room;
        this.paymentStatus = "PAID";
    }
}

// ---------- Hotel System Class ----------
public class HotelReservationSystemTask4 {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        initializeRooms();

        while (true) {
            System.out.println("\n--- HOTEL RESERVATION SYSTEM ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewRooms();
                case 2 -> bookRoom();
                case 3 -> cancelBooking();
                case 4 -> viewBookings();
                case 5 -> {
                    saveData();
                    System.out.println("Thank you! Visit again.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ---------- Initialize Rooms ----------
    static void initializeRooms() {
        if (rooms.isEmpty()) {
            rooms.add(new Room(101, "Standard", 1500));
            rooms.add(new Room(102, "Deluxe", 2500));
            rooms.add(new Room(103, "Suite", 4000));
        }
    }

    // ---------- View Rooms ----------
    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room r : rooms) {
            if (r.isAvailable) {
                System.out.println("Room ID: " + r.roomId + " | Category: " + r.category + " | Price: ₹" + r.price);
            }
        }
    }

    // ---------- Book Room ----------
    static void bookRoom() {
        System.out.print("Enter your name: ");
        sc.nextLine();
        String name = sc.nextLine();
        viewRooms();
        System.out.print("Enter Room ID to book: ");
        int id = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomId == id && r.isAvailable) {
                r.isAvailable = false;
                Booking b = new Booking(bookings.size() + 1, name, r);
                bookings.add(b);
                System.out.println("Payment Successful! Room Booked.");
                return;
            }
        }
        System.out.println("Room not available.");
    }

    // ---------- Cancel Booking ----------
    static void cancelBooking() {
        System.out.print("Enter Booking ID: ");
        int bid = sc.nextInt();

        Iterator<Booking> itr = bookings.iterator();
        while (itr.hasNext()) {
            Booking b = itr.next();
            if (b.bookingId == bid) {
                b.room.isAvailable = true;
                itr.remove();
                System.out.println("Booking cancelled. Refund initiated.");
                return;
            }
        }
        System.out.println("Booking not found.");
    }

    // ---------- View Bookings ----------
    static void viewBookings() {
        System.out.println("\nBooking Details:");
        for (Booking b : bookings) {
            System.out.println("Booking ID: " + b.bookingId + " | Name: " + b.customerName +
                    " | Room: " + b.room.category + " | Status: " + b.paymentStatus);
        }
    }

    // ---------- File Save ----------
    static void saveData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hotel_data.dat"));
            oos.writeObject(rooms);
            oos.writeObject(bookings);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error saving data");
        }
    }

    // ---------- File Load ----------
    static void loadData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hotel_data.dat"));
            rooms = (ArrayList<Room>) ois.readObject();
            bookings = (ArrayList<Booking>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            rooms = new ArrayList<>();
            bookings = new ArrayList<>();
        }
    }
} 
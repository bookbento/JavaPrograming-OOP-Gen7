package com.library.lab01.lab04;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagementApp {

    public static void main(String[] args) {

        System.out.println("\n" + "=".repeat(60));
        System.out.println(" LIBRARY MANAGEMENT SYSTEM - POLYMORPHISM DEMO");
        System.out.println("=".repeat(60));

        // ================= CREATE ITEMS =================

        List<LibraryItem> items = new ArrayList<>();

        items.add(new PhysicalBook("Java Programming", "John Smith",
                "978-0134685991", 450.0, "A1-04"));

        items.add(new PhysicalBook("Clean Code", "Robert Martin",
                "978-0132350884", 520.0, "B2-15"));

        items.add(new PhysicalBook("Design Patterns", "Gang of Four",
                "978-0201633612", 680.0, "A3-22"));

        items.add(new EBook("Effective Java", "Joshua Bloch",
                "978-0134685991",
                "https://library.ebooks.com/effective-java.pdf", 5.2));

        items.add(new EBook("Python Crash Course", "Eric Matthes",
                "978-1593279288",
                "https://library.ebooks.com/python-crash.pdf", 8.7));

        Member member1 = new Member("M001", "Somsak");
        Member member2 = new Member("M002", "Suda");

        // ================= DISPLAY ALL =================

        System.out.println("\n--- ALL LIBRARY ITEMS (Polymorphism Demo) ---");
        System.out.println("Calling printSummary() on each item in the list:\n");

        for (LibraryItem item : items) {
            item.printSummary();
        }

        // ================= CHECKOUT =================

        System.out.println("\n--- TESTING CHECKOUT FUNCTIONALITY ---");

        System.out.println("\nMember Somsak borrows Physical Book:");
        items.get(0).checkOut(member1);

        System.out.println("\nMember Suda borrows E-Book:");
        items.get(4).checkOut(member2);

        System.out.println("\nAttempting to checkout an already borrowed item:");
        items.get(0).checkOut(member2);

        // ================= STATUS AFTER CHECKOUT =================

        System.out.println("\n--- ITEMS STATUS AFTER CHECKOUT ---");

        for (LibraryItem item : items) {

            if (item instanceof PhysicalBook pb) {
                System.out.println("PhysicalBook[Title='" + pb.getTitle() +
                        "', Location='" + pb.getShelfLocation() +
                        "', Status='" + pb.getStatus() + "']");
            }

            else if (item instanceof EBook eb) {
                System.out.println("EBook[Title='" + eb.getTitle() +
                        "', Size='" + String.format("%.2f", eb.getFileSize()) +
                        " MB', Status='" + eb.getStatus() + "']");
            }
        }

        // ================= RETURN =================

        System.out.println("\n--- TESTING RETURN FUNCTIONALITY ---");
        System.out.println("\nReturning Physical Book:");
        items.get(0).returnItem();

        // ================= BORROW LIMIT =================

        System.out.println("\n--- TESTING BORROW LIMIT ---");

        items.get(0).checkOut(member1);
        items.get(1).checkOut(member1);
        items.get(2).checkOut(member1);
        items.get(3).checkOut(member1); // limit reached

        // ================= LATE FEE =================

        System.out.println("\n" + "=".repeat(60));
        System.out.println(" METHOD OVERRIDING: LATE FEE CALCULATION DEMO");
        System.out.println("=".repeat(60));

        int daysLate = 5;

        System.out.println("\n--- Late Fee Calculation (" + daysLate + " days late) ---");
        System.out.println("Physical Books (5 Baht per day late fee) and E-Books (NO late fees - files auto-expire):");

        for (LibraryItem item : items) {
            double lateFee = item.calculateLateFee(daysLate);
            System.out.printf("%s: %.2f Baht%n", item.getTitle(), lateFee);
        }

        // ================= INTERFACE DEMO =================

        System.out.println("\n" + "=".repeat(60));
        System.out.println(" INTERFACE IMPLEMENTATION: DIGITAL CONTENT & TAXABLE DEMO");
        System.out.println("=".repeat(60));

        // DIGITAL CONTENT

        System.out.println("\n--- DIGITAL CONTENT INTERFACE ---");
        System.out.println("Processing Digital Content for EBooks (DigitalContent interface):");
        System.out.println("Note: Only EBooks implement DigitalContent, PhysicalBooks do NOT.\n");

        for (LibraryItem item : items) {
            if (item instanceof DigitalContent digitalBook) {
                System.out.println("Processing EBook: " + item.getTitle());
                launchStreamingPlayer(digitalBook);
            }
        }

        // TAXABLE

        System.out.println("\n--- TAXABLE INTERFACE ---");
        System.out.println("Processing Tax Calculation (Taxable interface):");
        System.out.println("Both EBooks and PhysicalBooks implement Taxable.\n");

        for (LibraryItem item : items) {
            Taxable taxableItem = (Taxable) item;
            double tax = taxableItem.calculateTax();
            System.out.printf("%s: Price = %.2f Baht, Tax = %.2f Baht, Total = %.2f Baht%n",
                    item.getTitle(), item.getPrice(), tax, item.getPrice() + tax);
        }

        // ================= CHALLENGE =================

        List<DigitalContent> digitalContent = new ArrayList<>();

        LibraryMovie movie1 = new LibraryMovie("The Matrix", "Lana Wachowski",
                "https://streaming.library.com/matrix.mp4",
                136, 1999, "Sci-Fi", 199.0);

        LibraryMovie movie2 = new LibraryMovie("Inception", "Christopher Nolan",
                "https://streaming.library.com/inception.mp4",
                148, 2010, "Sci-Fi/Thriller", 249.0);

        digitalContent.add(movie1);
        digitalContent.add(movie2);

        digitalContent.add(new EBook("Effective Java", "Joshua Bloch",
                "978-0134685991",
                "https://library.ebooks.com/effective-java.pdf", 5.2));

        System.out.println("\n" + "=".repeat(60));
        System.out.println(" UNIVERSAL STREAMING PLAYER (Polymorphism via Interfaces)");
        System.out.println("=".repeat(60));

        for (DigitalContent content : digitalContent) {

            if (content instanceof LibraryMovie movie) {
                System.out.println(" STREAMING PLAYER - Playing Movie: " + movie.getTitle());
            } else if (content instanceof EBook book) {
                System.out.println(" STREAMING PLAYER - Reading E-Book: " + book.getTitle());
            }

            System.out.println("---");
            launchStreamingPlayer(content);
        }
    }

    public static void launchStreamingPlayer(DigitalContent content) {
        System.out.println("Connecting to streaming service...");
        System.out.println("Loading content...\n");
        content.streamOnline();
        System.out.println("User requests offline copy:");
        content.download();
    }
}
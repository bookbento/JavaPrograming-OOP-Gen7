package com.library.lab01.lab03;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagementApp {

    public static void main(String[] args) {

        System.out.println("\n" + "=".repeat(60));
        System.out.println(" LIBRARY MANAGEMENT SYSTEM - POLYMORPHISM DEMO");
        System.out.println("=".repeat(60));

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

        System.out.println("\n--- ALL LIBRARY ITEMS (Polymorphism Demo) ---");
        System.out.println("Calling displayDetails() on each item in the list:\n");

        for (LibraryItem item : items) {
            item.displayDetails();
        }

        System.out.println("\n--- TESTING CHECKOUT FUNCTIONALITY ---");

        System.out.println("\nMember Somsak borrows Physical Book:");
        items.get(0).checkOut(member1);

        System.out.println("\nMember Suda borrows E-Book:");
        items.get(4).checkOut(member2);

        System.out.println("\nAttempting to checkout an already borrowed item:");
        items.get(0).checkOut(member2);

        System.out.println("\n--- ITEMS STATUS AFTER CHECKOUT ---");
        for (LibraryItem item : items) {
            if (item instanceof PhysicalBook pb) {
                pb.printSummary();
            }
        }

        System.out.println("\n--- TESTING RETURN FUNCTIONALITY ---");
        items.get(0).returnItem();

        System.out.println("\n--- TESTING BORROW LIMIT ---");
        items.get(0).checkOut(member1);
        items.get(1).checkOut(member1);
        items.get(2).checkOut(member1);
        items.get(4).checkOut(member1); // Should be denied

        System.out.println("\n--- FINAL LIBRARY STATUS (Polymorphism Demo) ---");
        for (LibraryItem item : items) {
            item.displayDetails();
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println(" METHOD OVERRIDING: LATE FEE CALCULATION DEMO");
        System.out.println("=".repeat(60));

        int daysLate = 5;

        System.out.println("\n--- Late Fee Calculation (" + daysLate + " days late) ---");

        System.out.println("\nPhysical Books (5 Baht per day late fee):");
        for (LibraryItem item : items) {
            if (item instanceof PhysicalBook) {
                double fee = item.calculateLateFee(daysLate);
                System.out.printf(" %s: %.2f Baht\n", item.getTitle(), fee);
            }
        }

        System.out.println("\nE-Books (NO late fees - files auto-expire):");
        for (LibraryItem item : items) {
            if (item instanceof EBook) {
                double fee = item.calculateLateFee(daysLate);
                System.out.printf(" %s: %.2f Baht\n", item.getTitle(), fee);
            }
        }
    }
}
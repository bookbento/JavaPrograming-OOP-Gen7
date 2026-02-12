package Lab02;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagementApp {

    public static void main(String[] args) {

        System.out.println("\n" + "=".repeat(60));
        System.out.println("LIBRARY MANAGEMENT SYSTEM - DEMONSTRATION");
        System.out.println("=".repeat(60));

        List<Book> books = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            books.add(new Book("Java Programming", "John Smith", "123-456", 450.0, "Available"));
            books.add(new Book("OOP Concepts", "Will Jones", "999-888", 590.0, "Borrowed"));
        }

        Member member1 = new Member("M001", "Somsak");

        // Initial Info
        System.out.println("\n--- Initial Book Information ---");
        for (Book book : books) {
            book.printSummary();
        }

        // Testing checkOut
        System.out.println("\n--- Testing checkOut() Method ---");
        System.out.println("\nAttempting to checkout Book 1 and Book 2 (Available):");

        member1.borrowBook(books.get(0));
        member1.borrowBook(books.get(2));

        System.out.println("\nAttempting to checkout Book 2 again (Already Borrowed):");
        member1.borrowBook(books.get(1));

        // Testing return
        System.out.println("\n--- Testing returnBook() Method ---");
        System.out.println("\nReturning Book 1:");
        member1.returnBook(books.get(0));

        // Borrowing limit test
        System.out.println("\n--- Challenging Scenario: Borrowing Limit ---");

        member1.borrowBook(books.get(0));
        member1.borrowBook(books.get(4));
        member1.borrowBook(books.get(5)); // should deny here

        // Final Info
        System.out.println("\n--- Final Book Information ---");
        for (Book book : books) {
            book.printSummary();
        }
    }
}

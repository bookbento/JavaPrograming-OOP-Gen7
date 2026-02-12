package Lab02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Book class representing a book in the library management system.
 * Handles book checkout, return, and display operations.
 */
public class Book {
    // Attributes
    private String title;
    private String author;
    private String isbn;
    private double price;
    private String status; // "Available" or "Borrowed"
    private LocalDate returnDueDate; // Return due date (null when available)

    /**
     * Constructor to create a Book object.
     * 
     * @param title The title of the book
     * @param author The author of the book
     * @param isbn The ISBN of the book
     * @param price The price of the book in Baht
     * @param status The initial status ("Available" or "Borrowed")
     */
    public Book(String title, String author, String isbn, double price, String status) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.status = status;
        this.returnDueDate = null; // Initially no due date
        
        // If book is initially borrowed, set a return due date
        if ("Borrowed".equals(status)) {
            this.returnDueDate = LocalDate.now().plusDays(14);
        }
    }

    /**
     * Checks out the book by changing its status from "Available" to "Borrowed".
     * Sets the return due date to 14 days from today.
     * Prevents checking out a book that is already borrowed.
     */
    public void checkOut(Member borrower) {
        if ("Borrowed".equals(this.status)) {
            System.out.println("Error: Book '" + this.title + "' is already borrowed and cannot be checked out again.");
            return;
        }

        this.status = "Borrowed";
        this.returnDueDate = LocalDate.now().plusDays(14);

        System.out.println("Book '" + this.title + "' has been checked out successfully.");
        System.out.println("Book [" + this.title + "] has been borrowed by [" + borrower.getName() + "].");
        System.out.println("Return Due Date: " + this.returnDueDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    /**
     * Returns the book by changing its status from "Borrowed" to "Available".
     * Clears the return due date.
     */
    public void returnBook() {
        if ("Available".equals(this.status)) {
            System.out.println("Error: Book '" + this.title + "' is already available and cannot be returned.");
            return;
        }
        
        this.status = "Available";
        this.returnDueDate = null;
        System.out.println("Book '" + this.title + "' has been returned successfully.");
    }

    public void printSummary() {
        System.out.println("Book[Title='" + title + "', Status='" + status + "']");
    }


    /**
     * Displays all information about the book to the console.
     */
    public void displayDetails() {
        System.out.println("- Title: " + this.title);
        System.out.println("- Author: " + this.author);
        System.out.println("- ISBN: " + this.isbn);
        System.out.println("- Price: " + this.price + " Baht");
        System.out.println("- Status: " + this.status);
        
        if (this.returnDueDate != null) {
            System.out.println("- Return Due Date: " + this.returnDueDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        } else {
            System.out.println("- Return Due Date: N/A (Book is available)");
        }
    }

    // Getters and Setters (optional, but good practice)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getReturnDueDate() {
        return returnDueDate;
    }

    public void setReturnDueDate(LocalDate returnDueDate) {
        this.returnDueDate = returnDueDate;
    }
}

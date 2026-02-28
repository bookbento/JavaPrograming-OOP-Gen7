package com.library.lab01.lab04;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Book class representing a book in the library management system.
 */
public class Book {

    // Attributes
    private String title;
    private String author;
    private String isbn;
    private double price;
    private String status; // "Available" or "Borrowed"
    private LocalDate returnDueDate;

    // ✅ เพิ่มใหม่
    private String shelfLocation;
    private double fileSize; // สำหรับ eBook (MB)

    /**
     * Constructor
     */
    public Book(String title, String author, String isbn,
                double price, String status,
                String shelfLocation, double fileSize) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.status = status;
        this.shelfLocation = shelfLocation;
        this.fileSize = fileSize;

        if ("Borrowed".equals(status)) {
            this.returnDueDate = LocalDate.now().plusDays(14);
        } else {
            this.returnDueDate = null;
        }
    }

    // =========================
    // Business Methods
    // =========================

    public void checkOut() {
        if ("Borrowed".equals(this.status)) {
            System.out.println("Error: Book '" + this.title + "' is already borrowed.");
            return;
        }

        this.status = "Borrowed";
        this.returnDueDate = LocalDate.now().plusDays(14);

        System.out.println("Book '" + this.title + "' checked out.");
        System.out.println("Return Due Date: " +
                this.returnDueDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public void returnBook() {
        if ("Available".equals(this.status)) {
            System.out.println("Error: Book '" + this.title + "' is already available.");
            return;
        }

        this.status = "Available";
        this.returnDueDate = null;

        System.out.println("Book '" + this.title + "' returned successfully.");
    }

    public void displayDetails() {
        System.out.println("==== Book Details ====");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Price: " + price + " Baht");
        System.out.println("Shelf Location: " + shelfLocation);
        System.out.println("File Size: " + fileSize + " MB");
        System.out.println("Status: " + status);

        if (returnDueDate != null) {
            System.out.println("Return Due Date: " +
                    returnDueDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        } else {
            System.out.println("Return Due Date: N/A");
        }

        System.out.println("======================");
    }

    // =========================
    // Getters & Setters
    // =========================

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getReturnDueDate() { return returnDueDate; }
    public void setReturnDueDate(LocalDate returnDueDate) {
        this.returnDueDate = returnDueDate;
    }

    public String getShelfLocation() { return shelfLocation; }
    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public double getFileSize() { return fileSize; }
    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }
}
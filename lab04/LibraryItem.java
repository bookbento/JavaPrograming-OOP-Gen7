package com.library.lab01.lab04;

import java.time.LocalDate;

public abstract class LibraryItem {

    protected String title;
    protected String author;
    protected String isbn;
    protected double price;

    protected boolean isBorrowed = false;
    protected LocalDate returnDueDate;

    public LibraryItem(String title, String author, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    // ================= GETTERS =================

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return isBorrowed ? "Borrowed" : "Available";
    }

    public LocalDate getReturnDueDate() {
        return returnDueDate;
    }

    // ================= BORROW / RETURN =================

    public void checkOut(Member member) {

        if (isBorrowed) {
            System.out.println(title + " is already borrowed.");
            return;
        }

        isBorrowed = true;
        returnDueDate = LocalDate.now().plusDays(7);

        System.out.println(title + " checked out successfully by " + member.getName());
    }

    public void returnItem() {
        isBorrowed = false;
        returnDueDate = null;
        System.out.println(title + " returned successfully.");
    }

    // ================= ABSTRACT METHODS =================

    public abstract void printSummary();

    public abstract double calculateLateFee(int daysLate);
}
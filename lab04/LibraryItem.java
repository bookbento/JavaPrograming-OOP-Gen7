package com.library.lab01.lab04;

import java.time.LocalDate;

public abstract class LibraryItem {

    protected String title;
    protected String author;
    protected String isbn;
    protected double price;

    protected String status = "Available";
    protected LocalDate returnDueDate;
    protected Member borrowedBy;

    public LibraryItem(String title, String author, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    public String getTitle() { return title; }
    public double getPrice() { return price; }

    public void checkOut(Member member) {

        if ("Borrowed".equals(status)) {
            System.out.println("Error: Item '" + title +
                    "' is already borrowed and cannot be checked out again.");
            return;
        }

        if (!member.canBorrow()) {
            System.out.println("Borrow request denied for member " + member.getName() + ".");
            return;
        }

        status = "Borrowed";
        borrowedBy = member;
        returnDueDate = LocalDate.now().plusDays(14);
        member.borrowItem();

        System.out.println("Item '" + title + "' has been checked out successfully.");
        System.out.println("Item '" + title + "' has been borrowed by " + member.getName() + ".");
        System.out.println("Return Due Date: " + returnDueDate);
    }

    public void returnItem() {
        status = "Available";
        borrowedBy.returnItem();
        borrowedBy = null;
        returnDueDate = null;

        System.out.println("Item '" + title + "' has been returned successfully.");
    }

    public abstract void printSummary();
    public abstract double calculateLateFee(int daysLate);
}
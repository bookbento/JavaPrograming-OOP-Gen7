package com.library.lab01.lab03;

import java.time.LocalDate;

public abstract class LibraryItem {

    protected String title;
    protected String author;
    protected String isbn;
    protected String status = "Available";
    protected LocalDate returnDueDate;
    protected Member borrowedBy;

    public LibraryItem(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public void checkOut(Member member) {

        if ("Borrowed".equals(status)) {
            System.out.println("Error: Item '" + title +
                    "' is already borrowed and cannot be checked out again.");
            return;
        }

        if (!member.canBorrow()) {
            System.out.println("Error: " + member.getName()
                    + " has reached borrow limit.");
            return;
        }

        status = "Borrowed";
        borrowedBy = member;
        returnDueDate = LocalDate.now().plusDays(14);
        member.borrowItem(this);

        System.out.println("Item '" + title + "' has been checked out successfully.");
        System.out.println("Item '" + title + "' has been borrowed by " + member.getName() + ".");
        System.out.println("Return Due Date: " + returnDueDate);
    }

    public void returnItem() {
        status = "Available";
        borrowedBy = null;
        returnDueDate = null;
        System.out.println("Item '" + title + "' has been returned successfully.");
    }

    public abstract void displayDetails();

    public abstract double calculateLateFee(int daysLate);

    public String getTitle() {
        return title;
    }
}
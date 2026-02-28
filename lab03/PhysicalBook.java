package com.library.lab01.lab03;

public class PhysicalBook extends LibraryItem {

    private double price;
    private String shelfLocation;

    public PhysicalBook(String title, String author, String isbn,
                        double price, String shelfLocation) {

        super(title, author, isbn);
        this.price = price;
        this.shelfLocation = shelfLocation;
    }

    @Override
    public void displayDetails() {
        System.out.println("PHYSICAL BOOK");
        System.out.println("- Title: " + title);
        System.out.println("- Author: " + author);
        System.out.println("- ISBN: " + isbn);
        System.out.println("- Price: " + price + " Baht");
        System.out.println("- Shelf Location: " + shelfLocation);
        System.out.println("- Status: " + status);

        if (returnDueDate != null)
            System.out.println("- Return Due Date: " + returnDueDate);
        else
            System.out.println("- Return Due Date: N/A (Book is available)");

        System.out.println();
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 5.0;
    }

    public void printSummary() {
        System.out.println("PhysicalBook[Title='" + title +
                "', Location='" + shelfLocation +
                "', Status='" + status + "']");
    }
}
package com.library.lab01.lab04;

public class PhysicalBook extends LibraryItem implements Taxable {

    private String shelfLocation;

    public PhysicalBook(String title, String author, String isbn,
                        double price, String shelfLocation) {
        super(title, author, isbn, price);
        this.shelfLocation = shelfLocation;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    @Override
    public void printSummary() {
        System.out.println("PHYSICAL BOOK");
        System.out.println("- Title:            " + title);
        System.out.println("- Author:           " + author);
        System.out.println("- ISBN:             " + isbn);
        System.out.println("- Price:            " + price + " Baht");
        System.out.println("- Shelf Location:   " + shelfLocation);
        System.out.println("- Status:           " + getStatus());

        if (getReturnDueDate() != null)
            System.out.println("- Return Due Date:  " + getReturnDueDate());
        else
            System.out.println("- Return Due Date:  N/A (Book is available)");
        System.out.println();
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 5;
    }

    @Override
    public double calculateTax() {
        return price * 0.07;
    }
}
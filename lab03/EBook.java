package com.library.lab01.lab03;

public class EBook extends LibraryItem {

    private String downloadUrl;
    private double fileSize;

    public EBook(String title, String author, String isbn,
                 String downloadUrl, double fileSize) {

        super(title, author, isbn);
        this.downloadUrl = downloadUrl;
        this.fileSize = fileSize;
    }

    @Override
    public void displayDetails() {
        System.out.println("E-BOOK");
        System.out.println("- Title: " + title);
        System.out.println("- Author: " + author);
        System.out.println("- ISBN: " + isbn);
        System.out.println("- Download URL: " + downloadUrl);
        System.out.println("- File Size: " + fileSize + " MB");
        System.out.println("- Status: " + status);

        if (returnDueDate != null)
            System.out.println("- Return Due Date: " + returnDueDate);
        else
            System.out.println("- Return Due Date: N/A (E-book is available)");

        System.out.println();
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return 0.0; // No late fee for E-books
    }
}
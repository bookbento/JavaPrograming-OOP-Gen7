package com.library.lab01.lab04;

public class EBook extends LibraryItem implements DigitalContent, Taxable {

    private String downloadUrl;
    private double fileSize;

    public EBook(String title, String author, String isbn,
                 String downloadUrl, double fileSize) {

        super(title, author, isbn, 99.0);
        this.downloadUrl = downloadUrl;
        this.fileSize = fileSize;
    }

    public double getFileSize() {
        return fileSize;
    }

    @Override
    public void printSummary() {
        System.out.println("E-BOOK");
        System.out.println("- Title:            " + title);
        System.out.println("- ISBN:             " + isbn);
        System.out.println("- File Size:        " + String.format("%.2f", fileSize) + " MB");
        System.out.println("- Status:           " + getStatus());

        if (getReturnDueDate() != null)
            System.out.println("- Return Due Date:  " + getReturnDueDate());
        else
            System.out.println("- Return Due Date:  N/A (Item is available)");
        System.out.println();
    }

    @Override
    public void streamOnline() {
        System.out.println("Streaming '" + title + "' from URL: " + downloadUrl);
        System.out.println("Starting online stream... connected!");
    }

    @Override
    public void download() {
        System.out.println("Downloading '" + title + "' ("
                + String.format("%.2f", fileSize) + " MB)");
        System.out.println("Download complete!");
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return 0;
    }

    @Override
    public double calculateTax() {
        return price * 0.05;
    }
}
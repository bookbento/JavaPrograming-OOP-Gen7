package com.library.lab01.lab04;

public class EBook extends LibraryItem implements DigitalContent, Taxable {

    private String downloadUrl;
    private double fileSize;

    public EBook(String title, String author, String isbn,
                 String downloadUrl, double fileSize) {

        super(title, author, isbn, 99.0); // default 99 บาท
        this.downloadUrl = downloadUrl;
        this.fileSize = fileSize;
    }

    @Override
    public void printSummary() {
        System.out.println("- Title:            " + title);
        System.out.println("- ISBN:             " + isbn);
        System.out.println("- Status:           " + status);

        if (returnDueDate != null)
            System.out.println("- Return Due Date:  " + returnDueDate);
        else
            System.out.println("- Return Due Date:  N/A (Item is available)");
        System.out.println();
    }

    @Override
    public void streamOnline() {
        System.out.println("Streaming '" + title + "' from URL: " + downloadUrl);
        System.out.println("  Starting online stream... connected!");
        System.out.println("  You can now read the book online without downloading.");
    }

    @Override
    public void download() {
        System.out.println("Downloading '" + title + "' from URL: " + downloadUrl);
        System.out.println("  Downloading file... (" + String.format("%.2f", fileSize) + " MB)");
        System.out.println("  Download complete! File saved to your device.");
        System.out.println("  You can now read the book offline.");
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
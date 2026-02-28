package com.library.lab01.lab04;

public class LibraryMovie implements DigitalContent, Taxable {

    private String title;
    private String director;
    private String streamingUrl;
    private int durationMinutes;
    private int releaseYear;
    private String genre;
    private double price;

    public LibraryMovie(String title, String director,
                        String streamingUrl, int durationMinutes,
                        int releaseYear, String genre, double price) {
        this.title = title;
        this.director = director;
        this.streamingUrl = streamingUrl;
        this.durationMinutes = durationMinutes;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void streamOnline() {
        System.out.println("Streaming movie: " + title);
        System.out.println("Playing... Duration: " + durationMinutes + " minutes");
    }

    @Override
    public void download() {
        System.out.println("Downloading movie: " + title);
        System.out.println("Download complete!");
    }

    @Override
    public double calculateTax() {
        return price * 0.05; // Digital 5%
    }
}
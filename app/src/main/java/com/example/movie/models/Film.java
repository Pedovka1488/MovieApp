package com.example.movie.models;
public class Film {
    private String image;
    private String imdb;
    private String minutes;
    private String title;
    private String description;
    private double price;
    public Film() {

    }
    public Film(String image, String imdb, String minutes, String title, String description, double price) {
        this.image = image;
        this.imdb = imdb;
        this.minutes = minutes;
        this.title = title;
        this.description = description;
        this.price = price;
    }
    // Геттери та сеттери
    public String getImage() {
        return image;
    }
    public String getImdb() {
        return imdb;
    }
    public String getMinutes() {
        return minutes;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
}


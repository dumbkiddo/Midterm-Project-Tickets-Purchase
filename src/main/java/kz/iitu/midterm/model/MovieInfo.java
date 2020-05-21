package kz.iitu.midterm.model;


import kz.iitu.midterm.entity.Movie;

public class MovieInfo {
    private String code;
    private String name;
    private double price;

    public MovieInfo() {
    }

    public MovieInfo(Movie movie) {
        this.code = movie.getCode();
        this.name = movie.getName();
        this.price = movie.getPrice();
    }


    public MovieInfo(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
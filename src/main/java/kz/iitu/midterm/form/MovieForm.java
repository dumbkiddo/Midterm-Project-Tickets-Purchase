package kz.iitu.midterm.form;

import kz.iitu.midterm.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

public class MovieForm {
    private String code;
    private String name;
    private double price;

    private boolean newMovie = false;

    // Upload file.
    private MultipartFile fileData;

    public MovieForm() {
        this.newMovie = true;
    }

    public MovieForm(Movie movie) {
        this.code = movie.getCode();
        this.name = movie.getName();
        this.price = movie.getPrice();
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

    public MultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }

    public boolean isNewMovie() {
        return newMovie;
    }

    public void setNewMovie(boolean newMovie) {
        this.newMovie = newMovie;
    }

}

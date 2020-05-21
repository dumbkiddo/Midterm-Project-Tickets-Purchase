package kz.iitu.midterm.model;

public class CartLineInfo {

    private MovieInfo movieInfo;
    private int quantity;

    public CartLineInfo() {
        this.quantity = 0;
    }

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return this.movieInfo.getPrice() * this.quantity;
    }

}
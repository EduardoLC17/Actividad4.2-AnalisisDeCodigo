package video_store;

public class Rental {
    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return new Movie(movie);  //con un constructor copia
    }

    private Movie movie;
    private int daysRented;
}

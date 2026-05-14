package interfaces;


import model.Movie;

public interface MoviesManager {

    void addMovie(Movie movie);
    void deleteMovie(Movie movie);
    void updateMovie(Movie movie);

}

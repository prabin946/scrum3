package service;

import model.Movie;
import interfaces.Notification;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class MovieRecommender implements Notification<Movie> {

    private User userPreference;
    private List<Movie> moviesShowable;
    private final int MINIMUM_MATCH = 1;
    private MoviesManagement moviesManager;

    public MovieRecommender(User userPreference, MoviesManagement moviesManager) {
        this.userPreference = userPreference;
        this.moviesManager = moviesManager;
        this.moviesShowable = new ArrayList<>();
    }

    public boolean recommendedMovies() {
        List<Movie> allMovies = moviesManager.loadMovies();

        moviesShowable.clear();
        for (Movie movie : allMovies) {
            int score = scorebyActor(movie) + scorebyGenre(movie) + scorebyDirector(movie);
            if (score >= MINIMUM_MATCH) {
                addMovies(movie);
            }
        }
        return !moviesShowable.isEmpty();
    }

    public void addMovies(Movie movie) {
        moviesShowable.add(movie);
    }

    public void showMovies(List<Movie> movies) {
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". This movie matches your preferences: " + movies.get(i).getName());        }
    }

    public int scorebyActor(Movie movie) {
        for (String actors : movie.getActors()) {
            if (actors.equalsIgnoreCase(userPreference.getActorFav())) return 1;
        }
        return 0;
    }

    private int scorebyGenre(Movie movie) {
        return movie.getGenre().equals(userPreference.getGenreFav()) ? 1 : 0;
    }

    public int scorebyDirector(Movie movie) {
        return movie.getDirector().equalsIgnoreCase(userPreference.getDirectorFav()) ? 1 : 0;
    }

    @Override
    public boolean message(List<Movie> movies) {
        boolean found = recommendedMovies();

        if (found) {
            showMovies(moviesShowable);
            return true;
        } else {
            System.out.println("We're sorry! We couldn't find any movies for you.");
            return false;
        }
    }
}
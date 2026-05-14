package service;

import model.Genre;
import model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class MoviesManagement {

    private final Path moviesFile = Paths.get("src/data/movies.txt");
    public List<Movie> loadMovies() {
        List<Movie> movies = new ArrayList<>();

        if (!Files.exists(moviesFile)) {
            return movies;
        }

        try {
            List<String> allLines = Files.readAllLines(moviesFile);
            List<String> movieLines = new ArrayList<>();

            for (String line : allLines) {
                if (line.equals("------------------------")) {
                    if (!movieLines.isEmpty()) {
                        Movie movie = parseMovie(movieLines);
                        if (movie != null) {
                            movies.add(movie);
                        }
                        movieLines.clear();
                    }
                } else {
                    movieLines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading movies file: " + e.getMessage());        }
        return movies;
    }

    private Movie parseMovie(List<String> lines) {
        int id = 0;
        String name = "";
        String description = "";
        Genre genre = null;
        List<String> actors = new ArrayList<>();
        String director = "";
        int year = 0;

        for (String line : lines) {
            if (line.startsWith("id: ")) {
                id = Integer.parseInt(line.replace("id: ", "").trim());
            } else if (line.startsWith("name: ")) {
                name = line.replace("name: ", "").trim();
            } else if (line.startsWith("description: ")) {
                description = line.replace("description: ", "").trim();
            } else if (line.startsWith("genre: ")) {
                try {
                    String genreStr = line.replace("genre: ", "").trim().toUpperCase();
                    genre = Genre.valueOf(genreStr);
                } catch (IllegalArgumentException e) {
                    genre = null;
                }
            } else if (line.startsWith("actors: ")) {
                String actorsData = line.replace("actors: ", "").trim();
                if (!actorsData.isEmpty()) {
                    String[] parts = actorsData.split(", ");
                    for (String actor : parts) {
                        actors.add(actor);
                    }
                }
            } else if (line.startsWith("director: ")) {
                director = line.replace("director: ", "").trim();
            } else if (line.startsWith("year: ")) {
                year = Integer.parseInt(line.replace("year: ", "").trim());
            }
        }

        return new Movie(id, name, description, genre, actors, director, year);
    }


    public void addMovie(Movie movie) {
        try {
            String data = formatMovie(movie);
            Files.writeString(moviesFile, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error saving movie: " + e.getMessage());        }
    }

    private String formatMovie(Movie movie) {
        return "id: " + movie.getId() + "\n" +
                "name: " + movie.getName() + "\n" +
                "description: " + movie.getDescription() + "\n" + // ¡No olvides esta!
                "genre: " + movie.getGenre() + "\n" +
                "actors: " + String.join(", ", movie.getActors()) + "\n" +
                "director: " + movie.getDirector() + "\n" +
                "year: " + movie.getYear() + "\n" + // ¡Y esta!
                "------------------------\n";
    }
}
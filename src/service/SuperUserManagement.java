package service;

import model.Movie;
import model.User;
import interfaces.AdministratorUser;
import interfaces.MoviesManager;
import interfaces.Visualize;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class SuperUserManagement implements MoviesManager, AdministratorUser, Visualize {
    private final Path moviesFile = Paths.get("src/data/movies.txt");
    private final Path usersFile = Paths.get("src/data/users.txt");
    private String formatMovie(Movie movie) {
        return "id: " + movie.getId() + "\n" +
                "name: " + movie.getName() + "\n" +
                "description: " + movie.getDescription() + "\n" +
                "genre: " + movie.getGenre() + "\n" +
                "actors: " + String.join(", ", movie.getActors()) + "\n" +
                "director: " + movie.getDirector() + "\n" +
                "year: " + movie.getYear() + "\n" +
                "------------------------\n";
    }

    @Override
    public void addMovie(Movie movie) {
        String data = formatMovie(movie);
        try {
            Files.writeString(moviesFile, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error saving movie: " + e.getMessage());
        }
    }

    @Override
    public void deleteMovie(Movie movie) {
        try {
            List<String> allLines = Files.readAllLines(moviesFile);
            List<String> savesLines = new ArrayList<>();
            String foundId = "id: " + movie.getId();
            boolean saving = true;

            for (String lines: allLines) {
                if (lines.equalsIgnoreCase(foundId)) saving = false;
                if (saving) savesLines.add(lines);
                if (lines.equals("------------------------")) saving = true;
            }
            Files.write(moviesFile, savesLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateMovie(Movie movie) {
        try {
            List<String> allLines = Files.readAllLines(moviesFile);
            List<String> savesLines = new ArrayList<>();
            String foundId = "id: " + movie.getId();
            boolean isOldMovie = false;
            String newData = formatMovie(movie);

            for (String line : allLines) {
                if (line.trim().equals(foundId)) {
                    isOldMovie = true;
                    savesLines.add(newData.trim());
                }

                if (!isOldMovie) {
                    savesLines.add(line);
                }

                if (isOldMovie && line.equals("------------------------")) {
                    isOldMovie = false;
                }
            }

            Files.write(moviesFile, savesLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Movie updated successfully.");
        } catch (IOException e) {
            System.err.println("Error while updating: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            List<String> allLines = Files.readAllLines(usersFile);
            List<String> savesLines = new ArrayList<>();
            String foundId = "id: " + user.getiD();
            boolean saving = true;

            for (String lines: allLines) {
                if (lines.equalsIgnoreCase(foundId)) saving = false;
                if (saving) savesLines.add(lines);
                if (lines.equals("------------------------")) saving = true;
            }
            Files.write(usersFile, savesLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void visualizeMovie(){
        try {
           List<String> allLines = Files.readAllLines(moviesFile);

           for (String lines: allLines) {
               System.out.println(lines);
           }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void visualizeUsers() {
        try {
            List<String> allLines = Files.readAllLines(usersFile);

            for (String lines: allLines) {
                System.out.println(lines);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
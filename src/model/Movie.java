package model;

import java.util.List;

public class Movie {
    private int id;
    private String name;
    private String description;
    private Genre genre;
    private List<String> actors;
    private String director;
    private int year;

    public Movie(int id, String name, String description, Genre genre, List<String> actors, String director, int year) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.actors = actors;
        this.director = director;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<String> getActors() {
        return actors;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", actors=" + actors +
                ", director='" + director + '\'' +
                ", year=" + year +
                '}';
    }
}


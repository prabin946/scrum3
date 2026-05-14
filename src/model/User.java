package model;

import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private int iD;
    private String email;
    private String actorFav;
    private Genre genreFav;
    private String directorFav;
    private List<Movie> favoritesMovies;

    public User(int iD, String nombre, String password, String email, String actorFav, String genreFav, String directorFav) {
        super(nombre, password);
        this.iD = iD;
        this.email = email;
        this.actorFav = actorFav;
        this.genreFav = Genre.valueOf(genreFav.toUpperCase().trim());
        this.directorFav = directorFav;
        this.favoritesMovies = new ArrayList<>();
    }

    public int getiD() {
        return iD;
    }

    public String getEmail() {
        return email;
    }

    public String getActorFav() {
        return actorFav;
    }

    public Genre getGenreFav() {
        return genreFav;
    }

    public String getDirectorFav() {
        return directorFav;
    }

    public List<Movie> getFavoritesMovies() {
        return favoritesMovies;
    }

    @Override
    public String toString() {
        return "User{" +
                "iD='" + iD + '\'' +
                ", email='" + email + '\'' +
                ", actorFav='" + actorFav + '\'' +
                ", genreFav='" + genreFav + '\'' +
                ", directorFav='" + directorFav + '\'' +
                ", Favorites Movies: " + getFavoritesMovies() +
                '}';
    }
}

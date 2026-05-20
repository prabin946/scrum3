package model;

import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private int IdUser;
    private String email;
    private String actorFav;
    private Genre genreFav;
    private String directorFav;
    private List<Movie> favoritesMovies;

    public User(int IdUser, String nombre, String password, String email, String actorFav, String genreFav, String directorFav) {
        super(nombre, password);
        this.IdUser = IdUser;
        this.email = email;
        this.actorFav = actorFav;
        this.genreFav = Genre.valueOf(genreFav.toUpperCase().trim());
        this.directorFav = directorFav;
        this.favoritesMovies = new ArrayList<>();
    }

    public User(String name, String password, int idUser, String email) {
        super(name, password);
        IdUser = idUser;
        this.email = email;
    }

    public int getIdUser() {
        return IdUser;
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
                "iD='" + IdUser + '\'' +
                ", email='" + email + '\'' +
                ", actorFav='" + actorFav + '\'' +
                ", genreFav='" + genreFav + '\'' +
                ", directorFav='" + directorFav + '\'' +
                ", Favorites Movies: " + getFavoritesMovies() +
                '}';
    }
}

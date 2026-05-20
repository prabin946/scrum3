package model;

public class UserFavorites {

    private int idUser;
    private int idMovie;

    public UserFavorites(int idUser, int idMovie) {
        this.idUser = idUser;
        this.idMovie = idMovie;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }
}

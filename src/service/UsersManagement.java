package service;

import model.User;
import interfaces.UserAccess;
import interfaces.Visualize;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UsersManagement implements UserAccess, Visualize {
    private final Path moviesFile = Paths.get("src/data/movies.txt");
    private final Path usersFile = Paths.get("src/data/users.txt");

    private String formatUser(User user) {
        return  "id: " + user.getIdUser() + "\n" +
                "username: " + user.getName() + "\n" +
                "password: " + user.getPassword() + "\n" +
                "email: " + user.getEmail() + "\n" +
                "actor favorite: " + user.getActorFav() + "\n" +
                "genre favorite: " + user.getGenreFav() + "\n" +
                "director favorite: " + user.getDirectorFav() + "\n" +
                "------------------------\n";
    }

    @Override
    public void registerUser(User user) {
        try {
            String data = formatUser(user);
            if (Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$",user.getPassword())) {
                Files.writeString(usersFile, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } else {
                System.out.println("Registration failed: Password is too weak.");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public User loginUser(String username, String password) {
        try {
            List<String> allLines = Files.readAllLines(usersFile);
            List<String> userLines = new ArrayList<>();

            for (String line: allLines) {
                if (line.equals("------------------------")) {
                    User login = parseUser(userLines);

                    if (login.getName().equals(username) && login.getPassword().equals(password)) {
                        return login;
                    }

                    userLines.clear();
                } else {
                    userLines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public User parseUser(List<String> userLines) {
        int id = 0;
        String name = "", pass = "", email = "", actor = "", genre = "", director = "";

        for (String line : userLines) {
            if (line.startsWith("id: ")) id = Integer.parseInt(line.replace("id: ", ""));
            else if (line.startsWith("username: ")) name = line.replace("username: ", "");
            else if (line.startsWith("password: ")) pass = line.replace("password: ", "");
            else if (line.startsWith("email: ")) email = line.replace("email: ", "");
            else if (line.startsWith("actor favorite: ")) actor = line.replace("actor favorite: ", "");
            else if (line.startsWith("genre favorite: ")) genre = line.replace("genre favorite: ", "");
            else if (line.startsWith("director favorite: ")) director = line.replace("director favorite: ", "");
        }
        return new User(id, name, pass, email, actor, genre, director);
    }

    @Override
    public void visualizeMovie() {
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

    }


}

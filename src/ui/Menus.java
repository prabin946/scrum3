package ui;

import service.MovieRecommender;
import service.MoviesManagement;
import service.SuperUserManagement;
import service.UsersManagement;
import model.Genre;
import model.Movie;
import model.SuperUser;
import model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Menus {
    private final Scanner sc = new Scanner(System.in);
    private final SuperUserManagement adminManager;
    private final UsersManagement userManager;
    private final MoviesManagement moviesManager;
    private User currentUser;

    public Menus(SuperUserManagement adminManager, UsersManagement userManager, MoviesManagement moviesManager) {
        this.adminManager = adminManager;
        this.userManager = userManager;
        this.moviesManager = moviesManager;
    }

    public void mainMenu() {
        int option;
        do {
            System.out.println("\n=== MOVIE APP ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> handleRegister();
                case 2 -> handleLogin();
                case 3 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 3);
    }

    private void handleRegister() {
        System.out.println("\n--- USER REGISTRATION ---");
        System.out.print("Username: "); String name = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Favorite Actor: "); String actor = sc.nextLine();
        System.out.print("Favorite Genre: "); String genre = sc.nextLine();
        System.out.print("Favorite Director: "); String director = sc.nextLine();

        User newUser = new User(0, name, pass, email, actor, genre, director);
        userManager.registerUser(newUser);
    }

    private void handleLogin() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Username: "); String name = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();

        SuperUser adminReference = new SuperUser();

        if (name.equals(adminReference.getName()) && pass.equals(adminReference.getPassword())) {
            System.out.println("Access granted as ADMINISTRATOR.");            adminMenu();
        } else {
            currentUser = userManager.loginUser(name, pass);
            if (currentUser != null) {
                System.out.println("Login successful. Hello, " + currentUser.getName() + "!");
                userMenu();
            } else {
                System.out.println("Error: Invalid username or password.");
            }
        }
    }

    private void userMenu() {
        int option;
        do {
            System.out.println("\n--- WELCOME " + currentUser.getName().toUpperCase() + " ---");
            System.out.println("1. View all movies");
            System.out.println("2. Add movie to favorites");
            System.out.println("3. Get recommendations");
            System.out.println("4. Logout");
            System.out.print("Option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> moviesManager.loadMovies().forEach(m ->
                        System.out.println("ID: " + m.getId() + " | Name: " + m.getName()));
                case 2 -> {
                    System.out.print("Enter Movie ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                }
                case 3 -> {
                    MovieRecommender recommender = new MovieRecommender(currentUser, moviesManager);
                    recommender.message(new ArrayList<>());
                }
            }
        } while (option != 4);
    }

    private void adminMenu() {
        int option = 0;
        do {
            System.out.println("\n=== ADMINISTRATION PANEL ===");
            System.out.println("1. Add Movie");
            System.out.println("2. Update Movie");
            System.out.println("3. Delete Movie (by ID)");
            System.out.println("4. Delete User (by ID)");
            System.out.println("5. View User List");
            System.out.println("6. View Movie List");
            System.out.println("7. Logout");
            System.out.print("Select an option: ");

            try {
                option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 1 -> handleAddMovie();
                    case 2 -> handleUpdateMovie();
                    case 3 -> {
                        System.out.print("Enter Movie ID to delete: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Movie movieToDelete = new Movie(id, "", "", Genre.ROMANCE, new ArrayList<>(), "", 0);
                        adminManager.deleteMovie(movieToDelete);
                        System.out.println("Movie deleted successfully.");
                    }
                    case 4 -> {
                        System.out.print("Enter User ID to delete: ");
                        int id = Integer.parseInt(sc.nextLine());
                        User userToDelete = new User(id, "", "", "", "", "ROMANCE", "");
                        adminManager.deleteUser(userToDelete);
                        System.out.println("User deleted successfully.");
                    }
                    case 5 -> {
                        System.out.println("\n--- REGISTERED USERS LIST ---");
                        adminManager.visualizeUsers();
                    }
                    case 6 -> {
                        System.out.println("\n--- MOVIE LIST ---");
                        adminManager.visualizeMovie();
                    }
                    case 7 -> System.out.println("Logging out from Admin Panel...");
                }
            } catch (Exception e) {
                System.out.println("Error: Please make sure to enter valid data.");
            }
        } while (option != 7);
    }

    private void handleUpdateMovie() {
        System.out.print("Enter ID to update: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("New Name: "); String name = sc.nextLine();
        System.out.print("New Description: "); String desc = sc.nextLine();
        System.out.print("New Genre: "); String gen = sc.nextLine();
        System.out.print("New Director: "); String dir = sc.nextLine();
        System.out.print("New Year: "); int year = Integer.parseInt(sc.nextLine());
        Movie updated = new Movie(id, name, desc, Genre.valueOf(gen.toUpperCase()), new ArrayList<>(), dir, year);
        adminManager.updateMovie(updated);
    }

    private void handleAddMovie() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Description: "); String desc = sc.nextLine();
        System.out.print("Genre: "); String gen = sc.nextLine();
        System.out.print("Director: "); String dir = sc.nextLine();
        System.out.print("Year: "); int year = sc.nextInt(); sc.nextLine();

        Movie m = new Movie(id, name, desc, Genre.valueOf(gen.toUpperCase()), new ArrayList<>(), dir, year);
        moviesManager.addMovie(m);
    }


}

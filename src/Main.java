import service.MoviesManagement;
import service.SuperUserManagement;
import service.UsersManagement;
import ui.Menus;

public class Main {
    public static void main(String[] args) {
        UsersManagement userManager = new UsersManagement();
        MoviesManagement moviesManager = new MoviesManagement();
        SuperUserManagement adminManager = new SuperUserManagement();

        Menus menuSystem = new Menus(adminManager, userManager, moviesManager);
        menuSystem.mainMenu();
    }
}
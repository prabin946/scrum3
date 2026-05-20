package dao;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public final static String DATABSEURL = "jdbc:sqlite:"+Path.of("db","");
    public static Connection getConncetion() throws SQLException {
        return DriverManager.getConnection(DATABSEURL);
    }

    static void main() {
        try (Connection connection = DatabaseConnection.getConncetion()){


            System.out.println("Database Connected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

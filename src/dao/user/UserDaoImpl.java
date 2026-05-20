package dao.user;

import dao.DatabaseConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO {


    @Override
    public List<User> findAll() {

        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConncetion();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                while (resultSet.next()){
                    users.add(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
    public User mapRow(ResultSet resultSet) throws SQLException {

                int userId =  resultSet.getInt("IdUser");
                String name =  resultSet.getString("name");
                String password =  resultSet.getString("password");
                String email = resultSet.getString("email");
                return new User(name,password,userId,email);
    }

    @Override
    public User findById(int id) {

        String sql = "SELECT * FROM USER WHERE IdUser = ?";
        try (Connection connection = DatabaseConnection.getConncetion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            try (ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()){
                    return mapRow(resultSet);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public User create(User user) {

        String sql = "INSERT INTO user (name, password, email) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConncetion();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();

            try (ResultSet resultSet = ps.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int generatedId = resultSet.getInt(1);
                    return new User(user.getName(), user.getPassword(), generatedId, user.getEmail());
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return null;
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE user SET name = ?, password = ?, email = ? WHERE IdUser = ?";
        try (Connection connection = DatabaseConnection.getConncetion();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getIdUser());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(User object) {

        String sql = "DELETE FROM USER WHERE userId = ?";
        try (Connection connection = DatabaseConnection.getConncetion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            int rowAffected = preparedStatement.executeUpdate();

            return rowAffected == 1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}

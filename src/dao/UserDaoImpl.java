package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements  UserDAO{


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
            throw new RuntimeException(e);
        }
        return users;
    }
    public User mapRow(ResultSet resultSet){

        // Variables
        return null;
    }

    @Override
    public User findById(int id) {

        String sql = "SELECT * FROM USER WHERE userId = ?";
        try (Connection connection = DatabaseConnection.getConncetion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            try (ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()){
                    return mapRow(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public boolean update(User object) {
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

package dao.userFavorites;

import dao.DatabaseConnection;
import model.UserFavorites;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserFavoritesDAO{

    @Override
    public List<UserFavorites> findAll() {
        String sql = "SELECT * FROM userfavorites";
        List<UserFavorites> list = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConncetion();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public UserFavorites mapRow(ResultSet rs) throws SQLException {
        return new UserFavorites(
                rs.getInt("IdUser"),
                rs.getInt("IdMovie")
        );
    }
    @Override
    public UserFavorites findById(int id) {
        String sql = "SELECT * FROM userfavorites WHERE IdUser = ?";

        try (Connection connection = DatabaseConnection.getConncetion();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public UserFavorites create(UserFavorites userFavorites) {

        String sql = "INSERT INTO userfavorites (IdUser, IdMovie) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConncetion();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userFavorites.getIdUser());
            ps.setInt(2, userFavorites.getIdMovie());
            ps.executeUpdate();

            return userFavorites;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(UserFavorites object) {
        return false;
    }

    @Override
    public boolean delete(UserFavorites object) {
        String sql = "DELETE FROM userfavorites WHERE IdUser = ? AND IdMovie = ?";

        try (Connection connection = DatabaseConnection.getConncetion();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, object.getIdUser());
            ps.setInt(2, object.getIdMovie());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package dao;

import model.User;
import java.util.List;

public interface UserDAO {

    void add(User user);
    User findById(int id);
    List<User> findAll();
    boolean delete(int id);
}
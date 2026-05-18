package dao;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl {

    public static class UserDaoTxtImpl implements UserDAO {
        List<User> userList = new ArrayList<>();

        @Override
        public void add(User user) {
            userList.add(user);
        }

        @Override
        public User findById(int id) {
            for (User user : userList) {
                if (user.getiD() == id) {
                    return user;
                }
            }
            return null;
        }

        @Override
        public List<User> findAll() {
            return userList;
        }


        @Override
        public boolean delete(int id) {
            User userToRemove = null;
            for (User user : userList) {
                if (user.getiD() == id) {
                    userToRemove = user;
                    break;
                }
            }
            if (userToRemove != null) {
                userList.remove(userToRemove);
                return true;
            }
            return false;
        }
    }
}

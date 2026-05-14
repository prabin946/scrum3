package interfaces;

import model.User;

public interface UserAccess {
    void registerUser(User user);
    User loginUser(String username, String password);
}

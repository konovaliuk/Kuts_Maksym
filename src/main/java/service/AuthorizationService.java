package service;

import dao.DataBaseFactory;
import dao.UserDAO;
import enteties.User;

public class AuthorizationService {
    public static String registerUser(User user) {
        UserDAO userDAO = DataBaseFactory.getUserDAO();
        if (userDAO.findUserByUsername(user.getUsername()) != null) {

            return "usernameRegistered";
        } else if (userDAO.findUserByEmail(user.getEmail()) != null) {
            return "emailRegistered";
        } else {
            userDAO.addUser(user);
            return null;
        }
    }

    public static User getUserIfRegistered(String username, String password) {
        UserDAO userDAO = DataBaseFactory.getUserDAO();
        User userFromDB;
        if (username.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$")) {
            userFromDB = userDAO.findUserByEmail(username);

        } else {
            userFromDB = userDAO.findUserByUsername(username);

        }
        if(userFromDB != null && userFromDB.getPassword().equals(password)){
            userFromDB.setPassword(null);
            return userFromDB;
        }
        return null;
    }
}

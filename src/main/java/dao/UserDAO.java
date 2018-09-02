package dao;


import enteties.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);
    User findUserById(Long id);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    List<User> findAllUsers();
    List<User> findUsersDynamically(String sql, Object... values);
    void updateUserDynamically(String sql,Object... values);

}

package dao.impl;

import dao.UserDAO;
import dao.util.DBUtil;
import enteties.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;

    private UserDAOImpl(){}

    @Override
    public void addUser(User user) {
        String SQL = "INSERT INTO user(username,password,first_name,last_name,email) VALUES(?,?,?,?,?)";
        updateUserDynamically(SQL,user.getUsername(),user.getPassword(),user.getFirstName(),user.getLastName(),user.getEmail());
    }

    @Override
    public User findUserById(Long id) {
        String SQL = "SELECT * FROM user WHERE user_id = ?";
        List<User> users = findUsersDynamically(SQL,id);
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        String SQL = "SELECT * FROM user WHERE username = ?";
        List<User> users = findUsersDynamically(SQL,username);
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        String SQL = "SELECT * FROM user WHERE email = ?";
        List<User> users = findUsersDynamically(SQL,email);
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        String SQL = "SELECT * FROM user";
        return findUsersDynamically(SQL);
    }

    @Override
    public List<User> findUsersDynamically(String sql, Object... values) {
        return DBUtil.findObjectDynamically(DBUtil.ObjectType.User,sql,values);
    }

    @Override
    public void updateUserDynamically(String sql, Object... values) {
        DBUtil.updateObjectDynamically(sql,values);
    }

    public static UserDAOImpl getInstance(){
        if(instance == null){
            instance = new UserDAOImpl();
        }
        return instance;
    }
}

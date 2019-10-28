package pw.react.backend.reactbackend.dao;

import java.util.List;
import pw.react.backend.reactbackend.entity.User;

public interface UserDao {
    List<User> findAll();
    void insertUser(User usr);
    void updateUser(User usr);
    void executeUpdateUser(User usr);
    public void deleteUser(User usr);
}
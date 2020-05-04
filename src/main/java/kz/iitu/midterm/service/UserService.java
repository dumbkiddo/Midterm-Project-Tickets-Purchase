package kz.iitu.midterm.service;

import kz.iitu.midterm.entity.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(Long id, User user);
}

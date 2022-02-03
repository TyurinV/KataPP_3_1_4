package academy.kata.SpringBoot.service;

import academy.kata.SpringBoot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    List<User> getAllUsers();
    void add(User user);
    void remove(Long id);
    void edit(User user);
    User getUserById(Long id);
}

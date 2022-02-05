package academy.kata.SpringBoot.dao;



import academy.kata.SpringBoot.model.User;

import java.util.List;


public interface UserDAO {
    List<User> getAllUsers();
    void add(User user);
    void remove(Long id);
    void edit(User user);
    User getById(Long id);
    User getUserByName(String userName);
}

package academy.kata.SpringBoot.service;

import academy.kata.SpringBoot.dao.UserDAO;
import academy.kata.SpringBoot.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;

    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }


    public void add(User user) {
        userDAO.add(user);
    }


    public void remove(Long id) {
        userDAO.remove(id);
    }


    @Transactional
    public void edit(User user) {
        userDAO.edit(user);
    }


    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }
}

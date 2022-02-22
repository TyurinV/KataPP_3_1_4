package academy.kata.SpringBoot.service;

import academy.kata.SpringBoot.dao.UserDAO;
import academy.kata.SpringBoot.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }


    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.add(user);
    }


    public void remove(Long id) {
        userDAO.remove(id);
    }


    @Transactional
    public void edit(User user) {
        User target = getUserById(user.getId());
        if (user.getPassword() == "") {
            user.setPassword(passwordEncoder.encode(target.getPassword()));
        }
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

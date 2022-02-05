package academy.kata.SpringBoot.service.impl;

import academy.kata.SpringBoot.dao.UserDAO;
import academy.kata.SpringBoot.model.User;
import academy.kata.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

        private final UserDAO userDAO;


    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;

    }
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }


    public void add(User user) {
        userDAO.add(user);
    }
//    public void add(User user) {
//        userRepository.save(user);
//    }


    public void remove(Long id) {
        userDAO.remove(id);
    }
//    public void remove(Long id) {
//        userRepository.deleteById(id);
//    }


    @Transactional
    public void edit(User user) {
        userDAO.edit(user);
    }
//    public void edit(User user) {
//        userRepository.saveAndFlush(user);
//    }


    @Override
    public User getUserById(Long id) {
        return userDAO.getById(id);}
//    }    public User getUserById(Long id) {
//        return userRepository.findById(id).get();
//    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userDAO.getUserByName(userName);
    }

}

package academy.kata.SpringBoot.dao;

import academy.kata.SpringBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return em.createQuery("SELECT distinct u FROM User u JOIN FETCH u.roles ORDER BY u.id", User.class).getResultList();
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        em.persist(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    @Transactional
    public void edit(User user) {
        User target = getUserById(user.getId());
        if (user.getPassword() == "") {
            user.setPassword(passwordEncoder.encode(target.getPassword()));
        }
        em.merge(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String userName) {
        return em
                .createQuery("select u from User u JOIN FETCH u.roles where u.firstName = :username", User.class)
                .setParameter("username", userName)
                .getSingleResult();
    }
}
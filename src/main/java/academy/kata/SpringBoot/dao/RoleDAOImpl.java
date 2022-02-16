package academy.kata.SpringBoot.dao;

import academy.kata.SpringBoot.model.Role;
import academy.kata.SpringBoot.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return em
                .createQuery("SELECT r from Role r where r.roleName=:name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        em.persist(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> allRoles() {
        return em
                .createQuery("select r from Role r", Role.class)
                .getResultList();
    }
}


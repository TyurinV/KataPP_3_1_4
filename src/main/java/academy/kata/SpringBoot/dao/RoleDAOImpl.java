package academy.kata.SpringBoot.dao;

import academy.kata.SpringBoot.model.Role;
import academy.kata.SpringBoot.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Role getRoleByName(String name) {

        return em
                .createQuery("SELECT r from Role r where r.roleName=:name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public List<Role> allRoles() {
        return em
                .createQuery("select r from Role r", Role.class)
                .getResultList();
    }
}


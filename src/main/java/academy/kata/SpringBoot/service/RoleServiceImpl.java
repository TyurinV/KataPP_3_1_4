package academy.kata.SpringBoot.service;

import academy.kata.SpringBoot.dao.RoleDAO;
import academy.kata.SpringBoot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleByName(String name) {
        return this.roleDAO.getRoleByName(name);
    }

    @Override
    public void addRole(Role role) {
        roleDAO.addRole(role);}

    @Override
    public List <Role> getAllRoles() {
        return this.roleDAO.allRoles();
    }

    @Override
    public Role getRoleById(Long id) {
        return this.roleDAO.getRoleById(id);
    }
}

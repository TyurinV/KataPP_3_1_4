package academy.kata.SpringBoot.service;

import academy.kata.SpringBoot.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);

    void addRole(Role role);

    Role getRoleById(Long id);

    List<Role> getAllRoles();
}

package academy.kata.SpringBoot.service.impl;

import academy.kata.SpringBoot.model.Role;
import academy.kata.SpringBoot.repositories.RoleRepository;
import academy.kata.SpringBoot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

//    private final RoleDAO roleDAO;
//
//    @Autowired
//    public RoleServiceImpl(RoleDAO roleDAO) {
//        this.roleDAO = roleDAO;
//    }

    private final RoleRepository roleRepository;

    @Autowired

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return this.roleRepository.getRoleByName(name);
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }


    @Override
    public List <Role> getAllRoles() {
        return this.roleRepository.findAll();
    }


}

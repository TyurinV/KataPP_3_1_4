package academy.kata.SpringBoot.controllers;


import academy.kata.SpringBoot.exeptions.NoSuchUserExeption;
import academy.kata.SpringBoot.exeptions.UserIncorrectData;
import academy.kata.SpringBoot.model.Role;
import academy.kata.SpringBoot.model.User;
import academy.kata.SpringBoot.service.RoleService;
import academy.kata.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/api")
@RestController
public class AdminRestController {

    UserService userService;
    RoleService roleService;
    UserDetailsService userDetailsService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/")
    public String welcome() {
        return "allusers";
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserExeption("There is no user whith ID = " + id + " in Databese");
        }
        return user;
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.add(user);
        return user;
    }

    @PutMapping("/users")
    public User editUser(@RequestBody User user) {
        userService.edit(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.remove(id);
        return "User whith ID = " + id + " was deleted";
    }

    @GetMapping("/users/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}

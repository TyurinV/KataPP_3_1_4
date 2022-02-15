package academy.kata.SpringBoot.controllers;


import academy.kata.SpringBoot.exeptions.NoSuchUserExeption;
import academy.kata.SpringBoot.exeptions.UserIncorrectData;
import academy.kata.SpringBoot.model.User;
import academy.kata.SpringBoot.service.RoleService;
import academy.kata.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RequestMapping("/api")
@RestController
public class UserRestController {

    UserService userService;
    RoleService roleService;
    UserDetailsService userDetailsService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/")
    public String welcome() {
        return "allusers";
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserExeption("There is no user whith ID = " + id + " in Databese");
        }
        return user;
    }
    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.add(user);
        return user;
    }
    @Secured(value = {"ROLE_ADMIN"})
    @PutMapping("/users")
    public User editUser(@RequestBody User user) {
        userService.edit(user);
        return user;
    }
    @Secured(value = {"ROLE_ADMIN"})
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.remove(id);
        return "User whith ID = " + id + " was deleted";
    }


    @GetMapping("/userAuth/")
    public String getPrincipal(Authentication authentication) {
        String name = authentication.getName();
        String autorities = authentication.getAuthorities().toString();
        String result = name + " whith roles: " + autorities.substring(1, (autorities.length() - 1));
        return result.replaceAll("ROLE_", "");
    }


    @GetMapping("/currentUser")
    public User getAuthUser(@AuthenticationPrincipal User user) {
        User returnUser = userService.getUserByName(user.getUsername());
        return returnUser;
    }

}

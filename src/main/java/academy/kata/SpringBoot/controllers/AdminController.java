package academy.kata.SpringBoot.controllers;

import academy.kata.SpringBoot.model.Role;
import academy.kata.SpringBoot.model.User;
import academy.kata.SpringBoot.service.RoleService;
import academy.kata.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public AdminController(RoleService roleService, UserService userService, UserDetailsService userDetailsService) {
        this.roleService = roleService;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/")
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("username", userDetailsService.loadUserByUsername(principal.getName()));
        List<User> users = userService.getAllUsers();
        model.addAttribute("userList", users);
        return "allusers";
    }


    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(required = false) String roleAdmin) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        if (roleAdmin != null) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        }
        user.setRoles(roles);
        user.setPassword(hashedPassword);
        userService.add(user);
        return "redirect:/admin/";
    }


    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/delete/", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Long id) {
        userService.remove(id);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.GET})
    public String editUser(User user, @RequestParam(required = false) String roleAdmin) { // массив айдишников
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        if (roleAdmin != null) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        }
        user.setRoles(roles);
        user.setPassword(hashedPassword);
        userService.edit(user);
        return "redirect:/admin/";
    }
}

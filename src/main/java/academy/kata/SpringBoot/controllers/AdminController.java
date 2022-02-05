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
import java.util.Optional;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("username", userDetailsService.loadUserByUsername(principal.getName()));
        List<User> users = userService.getAllUsers();
        model.addAttribute("userList", users);
        return "allusers";
    }

    @GetMapping(value = "/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }


    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("user", new User());
        return "/add";
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


//    @GetMapping(value = "/delete/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userService.remove(id);
//        return "redirect:/admin/";
//    }

    @RequestMapping("/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/delete/", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Long id) {
        userService.remove(id);
        return "redirect:/admin/";
    }
    //    @PostMapping(value = "/edit")

    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.GET})
//    public String editUser(@ModelAttribute("user") User user, @RequestParam(required = false) String roleAdmin) { // массив айдишников
    public String editUser(User user, @RequestParam(required = false) String roleAdmin) { // массив айдишников
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("ROLE_USER"));
        if (roleAdmin != null) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        }
        user.setRoles(roles);
        userService.edit(user);
        return "redirect:/admin/";
    }

}

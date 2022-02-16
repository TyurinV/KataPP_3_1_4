package academy.kata.SpringBoot.controllers;

import academy.kata.SpringBoot.model.User;
import academy.kata.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/api")
@RestController
public class UserRestController {

    UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
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

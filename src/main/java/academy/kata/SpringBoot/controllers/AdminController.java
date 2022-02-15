package academy.kata.SpringBoot.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping(value = "/")
    public String getAllUsers() {
        return "allusers";
    }
}

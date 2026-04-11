package br.applogin.applogin.controller;

import br.applogin.applogin.model.User;
import br.applogin.applogin.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private final UserRepository ur;

    public LoginController(UserRepository ur) {

        this.ur = ur;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registerUser")
    public String register() {

        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult result) {

        System.out.println("CHEGOU NO BACKEND!");
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());


        if (result.hasErrors()) {
            return "redirect:/registerUser";
        }

        ur.save(user);
        return "redirect:/login";
    }

}

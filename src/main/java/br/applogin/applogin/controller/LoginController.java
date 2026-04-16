package br.applogin.applogin.controller;

import br.applogin.applogin.model.User;
import br.applogin.applogin.repository.UserRepository;
import br.applogin.applogin.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

    private final UserRepository ur;

    @Autowired
    public LoginController(UserRepository ur) {

        this.ur = ur;
    }


    @GetMapping("/")
    public String dashboard(Model model, HttpServletRequest request) throws UnsupportedEncodingException {

        String userId = CookieService.getCookie(request, "userId");

        if (userId == null) {
            return "redirect:/login";
        }

        model.addAttribute("nome", CookieService.getCookie(request, "userName"));
        return "cidadeviva";
    }



    @PostMapping("/logar")
    public String loginUser(User user, Model model, HttpServletResponse response) throws UnsupportedEncodingException {

        User usuarioLogado = this.ur.login(user.getEmail(), user.getPassword());

        if (usuarioLogado != null) {
            CookieService.setCookie(response,"userId", String.valueOf(usuarioLogado.getId()), 10000);
            CookieService.setCookie(response,"userName", String.valueOf(usuarioLogado.getName()), 10000);

            return "redirect:/";
        }
        model.addAttribute("erro", "Usuário Inválido");

        return "index";
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

    @GetMapping({"/login", "/login/"})
    public String login() {
        return "index";
    }

}

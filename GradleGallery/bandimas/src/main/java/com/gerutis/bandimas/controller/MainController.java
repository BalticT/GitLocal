package com.gerutis.bandimas.controller;

import com.gerutis.bandimas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private UserService service;

    @RequestMapping("/")
    public String root() {

        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("authenticated", service.isAuthenticated());

        return "index";
    }


    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("authenticated", service.isAuthenticated());
        if (service.isAuthenticated()) {

            return "redirect:/index";
        }
        return "login";

    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}

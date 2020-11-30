package com.example.hostelweb.controller;

import com.example.hostelweb.domain.Person;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model, @AuthenticationPrincipal Person person){
        if(person != null) {
            model.addAttribute("person", person);
        } else {
            model.addAttribute("person", -1);
        }
        return "index";
    }
}

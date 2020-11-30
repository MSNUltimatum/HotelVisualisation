package com.example.hostelweb.controller;

import com.example.hostelweb.domain.Person;
import com.example.hostelweb.service.PersonService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LoginAndCreateController {

    private final PersonService personService;

    @Autowired
    public LoginAndCreateController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @PostMapping("/createAccount")
    public String createAccount(Person person, Model model){
        Boolean isCreated = personService.createPerson(person);
            model.addAttribute("errMessage", null);
        if(!isCreated){
            model.addAttribute("errMessage", "Пароли не совпадают");
        }
        return "login";
    }
}

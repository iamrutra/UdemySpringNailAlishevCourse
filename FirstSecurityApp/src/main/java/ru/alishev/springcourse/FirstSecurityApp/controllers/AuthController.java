package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.services.RegistrService;
import ru.alishev.springcourse.FirstSecurityApp.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrService registrService;
    private final PersonValidator personValidator;

    @Autowired
    public AuthController(RegistrService registrService, PersonValidator personValidator) {
        this.registrService = registrService;
        this.personValidator = personValidator;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registr")
    public String registrPage(@ModelAttribute("person") Person person){
        return "auth/registr";
    }

    @PostMapping("/registr")
    public String performRegistr(@ModelAttribute("person") @Valid Person person,
                                 BindingResult bindingResult){

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registr";
        }
        registrService.register(person);

        return "redirect:/auth/login";
    }
}

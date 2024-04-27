package ru.alishev.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request, Model model){

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

//        System.out.println("Hello " + name + " " + surname);

        model.addAttribute("message", "Hello " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a,
                             @RequestParam("b") int b,
                             @RequestParam("action") String act,
                             Model model){

        switch (act) {
            case "mult":
                model.addAttribute("mult", "Res: " + (a * b));
                break;
            case "add":
                model.addAttribute("add", "Res: " + (a + b));
                break;
            case "sub":
                model.addAttribute("sub", "Res: " + (a - b));
                break;
            case "div":
                model.addAttribute("div", "Res: " + (a / b));
        }

        return "first/calculator";
    }
}

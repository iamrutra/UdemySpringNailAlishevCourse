package pl.artur.project1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.artur.project1.dao.PersonDAO;
import pl.artur.project1.model.Person;

@Controller
@RequestMapping("/people")
public class PersonControllers {

    private final PersonDAO personDAO;

    public PersonControllers(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String people(Model model) {
        model.addAttribute("people", personDAO.getAll());
        return "person/show";
    }

    @GetMapping("/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "person/info";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "person/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("person") Person person){
        personDAO.edit(person);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person/newPerson";
    }

    @PostMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        personDAO.newPerson(person);
        return "redirect:/people";
    }


}

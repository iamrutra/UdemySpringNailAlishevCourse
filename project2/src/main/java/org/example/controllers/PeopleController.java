package org.example.controllers;

import org.example.models.Book;
import org.example.models.Person;
import org.example.services.BookService;
import org.example.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/people")

public class PeopleController {

    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public PeopleController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }



    @GetMapping
    public String people(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }
    @GetMapping("/new")
    public String newPeople(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }
    @PostMapping("/new")
    public String newPeople(@ModelAttribute("person") Person person) {
        System.out.println(person.getYearOfBirth());
        peopleService.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editPeople(@PathVariable int id, Model model) {
        model.addAttribute("person", peopleService.findById(id));
        System.out.println( peopleService.findById(id));
        return "people/edit";
    }
    @PostMapping("/{id}/edit")
    public String editPeople(@ModelAttribute("person") Person person) {
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPeople(@PathVariable int id, Model model) {
        Person person = peopleService.findById(id);
        if (person != null) {
            List<Book> books = person.getBooks();
            model.addAttribute("person", person);
            model.addAttribute("books", books.isEmpty() ? null : books);
            model.addAttribute("diffDays", bookService.getDiffDays(id));
        }
        return "people/show";
    }

    @PostMapping("/{id}/delete")
    public String deletePeople(@PathVariable int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}

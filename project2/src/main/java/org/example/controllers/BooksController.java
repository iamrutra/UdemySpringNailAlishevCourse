package org.example.controllers;

import org.example.models.Book;
import org.example.models.Person;
import org.example.services.BookService;
import org.example.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final PeopleService peopleService;


    @Autowired
    public BooksController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String showBooksPage(Model model,
                                @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                @RequestParam(value = "books_per_page", defaultValue = "10", required = false) int booksPerPage,
                                @RequestParam(value = "sort_by_year", defaultValue = "false", required = false) boolean sortByYear) {
        Page<Book> bookPage = bookService.findAll(page, booksPerPage, sortByYear);
        model.addAttribute("books", bookPage);
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }
    @PostMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "books/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("ownerId", new Integer(0));// Добавляем ownerId в модель с каким-то начальным значением
        return "books/show";
    }
    @PostMapping("/{id}/removeOwner")
    public String removeOwner(@PathVariable int id, Model model){
        model.addAttribute("book", bookService.findById(id));
        Book book = bookService.findById(id);
        book.setTimeget(null);
        bookService.removeOwner(id);
        bookService.save(book);
        return "redirect:/books/" + id;
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        bookService.delete(id);
        return "redirect:/books";
    }
    @PostMapping("/{id}/setOwner")
    public String setOwner(@PathVariable int id, @RequestParam("ownerId") int ownerId) {
        Book book = bookService.findById(id);
        Person owner = peopleService.findById(ownerId);
        if (book != null && owner != null) {
            book.setTimeget(new Date());
            book.setOwner(owner);
            bookService.save(book);
        } else {
            // Обработка ошибки
        }
        return "redirect:/books/{id}";
    }

}

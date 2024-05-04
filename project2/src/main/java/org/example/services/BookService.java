package org.example.services;

import org.example.models.Book;
import org.example.models.Person;
import org.example.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;
    private final PeopleService peopleService;

    @Autowired
    public BookService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }


    public Book findById(int id) {
        return booksRepository.findById(id).stream().findFirst().orElse(null);
    }
    public List<Book> findAllByOwner(Person owner) {
        return booksRepository.findAllByOwner(owner);
    }


    public List<Long> getDiffTime(int id) {
        Person owner = peopleService.findById(id);
        List<Book> books = booksRepository.findAllByOwner(owner);
        List<Long> durations = new ArrayList<>();
        System.out.println(books);
        for (Book book : books) {
            if (book.getTimeget() != null) {
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime bookTime = book.getTimeget().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                durations.add(Duration.between(bookTime, currentTime).toDays());
            }
        }
        return durations;
    }
    public List<Duration> getDiffDays(int id) {
        List<Long> diffTime = getDiffTime(id);
        List<Duration> durations = new ArrayList<>();
        for (Long diff : diffTime) {
            durations.add(Duration.ofDays(diff));
        }
        return durations;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Page<Book> findAll(int page, int booksPerPage, boolean sort) {
        Pageable pageable = PageRequest.of(page, booksPerPage, Sort.by("year"));
        return booksRepository.findAll(pageable);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }
    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
    @Transactional
    public void removeOwner(int id) {
        Book book = findById(id);
        book.setOwner(null);
    }
    @Transactional
    public void setOwner(int id, Person owner) {
        Book book = findById(id);
        book.setOwner(owner);
    }

    public List<Book> findByTitleStartingWith(String title) {
        return booksRepository.findByTitleStartingWith(title);
    }
}

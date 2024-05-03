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
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Book findById(int id) {
        return booksRepository.findById(id).stream().findFirst().orElse(null);
    }

    public Duration getDiffTime(int id) {
        Book book = findById(id);
        if (book.getTimeget() != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime bookTime = book.getTimeget().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return Duration.between(bookTime, currentTime);
        }
        return null;
    }
    public long getDiffDays(int id) {
        Duration diffTime = getDiffTime(id);
        System.out.println(diffTime.toDays());
        return diffTime != null ? diffTime.toDays() : 0;
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
}

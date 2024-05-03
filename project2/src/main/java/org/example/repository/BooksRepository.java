package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.models.Book;
import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

}

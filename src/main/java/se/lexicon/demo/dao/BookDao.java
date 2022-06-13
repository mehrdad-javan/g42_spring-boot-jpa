package se.lexicon.demo.dao;

import se.lexicon.demo.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book save(Book book);

    Optional<Book> findById(int id);

    List<Book> findAll();

    void remove(Book book);

    Book update(Book book);

}

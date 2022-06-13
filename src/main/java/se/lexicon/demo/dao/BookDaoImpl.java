package se.lexicon.demo.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.demo.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book save(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Optional<Book> findById(int id) {
        Book book = entityManager.find(Book.class, id);
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("select b from Book b").getResultList();
    }

    @Override
    public void remove(Book book) {
        findById(book.getId()).orElseThrow(() -> new IllegalArgumentException("Data not found exception"));
        entityManager.remove(book);
    }

    @Override
    public Book update(Book book) {
        // find by id
        return entityManager.merge(book);
    }
}

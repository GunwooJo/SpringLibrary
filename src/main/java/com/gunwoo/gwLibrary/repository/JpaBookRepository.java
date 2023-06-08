package com.gunwoo.gwLibrary.repository;

import com.gunwoo.gwLibrary.domain.Book;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaBookRepository implements BookRepository{

  private final EntityManager em;

  public JpaBookRepository(EntityManager em) {
    this.em = em;
  }

  @Override
  public Book save(Book book) {
    em.persist(book);
    return book;
  }

  @Override
  public Optional<Book> findById(Long id) {
    Book book = em.find(Book.class, id);
    return Optional.ofNullable(book);
  }

  @Override
  public Optional<Book> findByName(String name) {
    List<Book> book = em.createQuery("select b from Book b where b.name=:name", Book.class).setParameter("name", name).getResultList();
    return book.stream().findAny();
  }

  @Override
  public List<Book> findAll() {
    return em.createQuery("select b from Book b", Book.class).getResultList();
  }

  @Override
  public Optional<Book> findAcceptableBook() { //작성필요.
    return Optional.empty();
  }
}

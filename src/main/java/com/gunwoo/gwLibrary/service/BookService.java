package com.gunwoo.gwLibrary.service;

import com.gunwoo.gwLibrary.domain.Book;
import com.gunwoo.gwLibrary.repository.BookRepository;
import com.gunwoo.gwLibrary.repository.MemoryBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class BookService {
  private final BookRepository repository;


  public BookService(BookRepository repository) {
    this.repository = repository;
  }

  public Long addBook(Book book) {
    repository.save(book);
    return book.getId();
  }

  public List<Book> findAllBook() {
    return repository.findAll();
  }

  public Optional<Book> findBook(Long bookId) {
    return repository.findById(bookId);
  }

  public Optional<Book> findAcceptableBook() {
    return repository.findAcceptableBook();
  }
}

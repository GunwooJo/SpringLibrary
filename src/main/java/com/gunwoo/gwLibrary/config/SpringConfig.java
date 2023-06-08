package com.gunwoo.gwLibrary.config;

import com.gunwoo.gwLibrary.repository.BookRepository;
import com.gunwoo.gwLibrary.repository.JpaBookRepository;
import com.gunwoo.gwLibrary.repository.JpaUserRepository;
import com.gunwoo.gwLibrary.repository.UserRepository;
import com.gunwoo.gwLibrary.service.BookService;
import com.gunwoo.gwLibrary.service.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
  private final EntityManager em;

  public SpringConfig(EntityManager em) {
    this.em = em;
  }

  @Bean
  public UserService userService() {
    return new UserService(userRepository());
  }

  @Bean
  public UserRepository userRepository() {
    return new JpaUserRepository(em);
  }

  @Bean
  public BookService bookService() {
    return new BookService(bookRepository());
  }

  @Bean
  public BookRepository bookRepository() {
    return new JpaBookRepository(em);
  }

}

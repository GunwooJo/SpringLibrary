package com.gunwoo.gwLibrary.domain;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "book_id")
  private Book book;

  public String getName() {
    return name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

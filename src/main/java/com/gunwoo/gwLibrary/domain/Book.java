package com.gunwoo.gwLibrary.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private boolean isBorrowed;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isBorrowed() {
    return isBorrowed;
  }

  public void setBorrowed(boolean isBorrowed) {
    this.isBorrowed = isBorrowed;
  }
}

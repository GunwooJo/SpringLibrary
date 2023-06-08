package com.gunwoo.gwLibrary.controller;

import com.gunwoo.gwLibrary.domain.Book;
import com.gunwoo.gwLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
  private BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }



  @GetMapping("/book/register")
  public String bookRegister() {
    return "/book/register";
  }
  @PostMapping("/book/register")
  public String createBook(BookForm form) {
    Book book = new Book();
    book.setName(form.getName());
    bookService.addBook(book);

    return "redirect:/book/register";
  }

  @GetMapping("/book/list")
  public String bookList(Model model) {
    List<Book> bookList = bookService.findAllBook();
    model.addAttribute("bookList", bookList);
    return "/book/list";
  }
}

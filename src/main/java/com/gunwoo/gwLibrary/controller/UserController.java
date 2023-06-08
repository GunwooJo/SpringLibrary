package com.gunwoo.gwLibrary.controller;

import com.gunwoo.gwLibrary.domain.User;
import com.gunwoo.gwLibrary.repository.MemoryUserRepository;
import com.gunwoo.gwLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/register")
  public String userRegister() {
    return "user/register";
  }

  @PostMapping("/user/register")
  public String createUser(UserForm form) {
    User user = new User();
    user.setUsername(form.getUsername());
    user.setPassword(form.getPassword());
    user.setRole("user");

    userService.addUser(user);

    return "redirect:/user/list";
  }

  @PostMapping("user/login")
  public String loginUser(UserForm form) {
    User user = new User();
    user.setUsername(form.getUsername());
    user.setPassword(form.getPassword());
    userService.validateDuplicateUser(user);
    return "/book/list";
  }

  @GetMapping("/user/list")
  public String userList(Model model) {
    List<User> userList = userService.findUsers();
    model.addAttribute("userList", userList);
    return "/user/list";
  }


}

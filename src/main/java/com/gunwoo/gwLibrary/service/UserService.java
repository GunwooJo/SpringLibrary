package com.gunwoo.gwLibrary.service;

import com.gunwoo.gwLibrary.domain.User;
import com.gunwoo.gwLibrary.repository.MemoryUserRepository;
import com.gunwoo.gwLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
-addUser()
-findUser()
-findMyBook()
-borrowBook()
-returnBook()
* */

@Transactional
public class UserService {
  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public Long addUser(User user) {
    validateDuplicateUser(user);
    repository.save(user);
    return user.getId();
  }

  public List<User> findUsers() {
    return repository.findAll();
  }

  public Optional<User> findOne(Long userId) {
    return repository.findById(userId);
  }

  public void validateDuplicateUser(User user) {
    repository.findByName(user.getName()).ifPresent(m->{
      throw new IllegalStateException("이미 존재하는 유저입니다.");
    });
  }
}

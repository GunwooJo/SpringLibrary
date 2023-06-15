package com.gunwoo.gwLibrary.service;

import com.gunwoo.gwLibrary.domain.User;
import com.gunwoo.gwLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Service
public class UserService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  public Long addUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
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

  public Optional<User> findByName(String username) {
    return repository.findByName(username);
  }

  public void validateDuplicateUser(User user) {
    repository.findByName(user.getUsername()).ifPresent(m->{
      throw new IllegalStateException("이미 존재하는 유저입니다.");
    });
  }
}

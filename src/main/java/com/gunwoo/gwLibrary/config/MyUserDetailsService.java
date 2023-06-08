package com.gunwoo.gwLibrary.config;

import com.gunwoo.gwLibrary.domain.User;
import com.gunwoo.gwLibrary.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
  private final UserService userService;

  public MyUserDetailsService(UserService userService) {
    this.userService = userService;
  }
  @Override
  public UserDetails loadUserByUsername(String insertedUserName) throws UsernameNotFoundException {
    Optional<User> result = userService.findByName(insertedUserName);
    User user = result.orElseThrow(()-> new UsernameNotFoundException("없는 회원입니다."));

    return org.springframework.security.core.userdetails.User.builder()
      .username(user.getUsername())
      .password(user.getPassword())
      .roles(user.getRole())
      .build();
  }
}

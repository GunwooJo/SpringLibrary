package com.gunwoo.gwLibrary.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// 참고: https://nahwasa.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-30%EC%9D%B4%EC%83%81-Spring-Security-%EA%B8%B0%EB%B3%B8-%EC%84%B8%ED%8C%85-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0
@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().disable()
      .authorizeHttpRequests(request -> request
        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
        .requestMatchers("/user/register").permitAll()
        .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
      )
      .formLogin(login -> login	// form 방식 로그인 사용
        .loginPage("/")	// [A] 커스텀 로그인 페이지 지정
        .loginProcessingUrl("/user/login")	// [B] submit 받을 url
        .usernameParameter("username")	// [C] submit할 아이디
        .passwordParameter("password")	// [D] submit할 비밀번호
        .defaultSuccessUrl("/book/list", true)	// 성공 시 dashboard로
        .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
      )
      .logout(withDefaults());	// 로그아웃은 기본설정으로 (/logout으로 인증해제)

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new SimplePasswordEncoder();
  }
}

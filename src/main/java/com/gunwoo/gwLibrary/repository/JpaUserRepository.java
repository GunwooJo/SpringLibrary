package com.gunwoo.gwLibrary.repository;

import com.gunwoo.gwLibrary.domain.User;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserRepository{
  private final EntityManager em;

  public JpaUserRepository(EntityManager em) {
    this.em = em;
  }

  @Override
  public User save(User user) {
    em.persist(user);
    return user;
  }

  @Override
  public Optional<User> findById(Long id) {
    User user = em.find(User.class, id);
    return Optional.ofNullable(user);
  }

  @Override
  public Optional<User> findByName(String username) {
    List<User> user = em.createQuery("select u from User u where u.username=:username", User.class).setParameter("username", username).getResultList();
    return user.stream().findAny();
  }

  @Override
  public List<User> findAll() {
    return em.createQuery("select u from User u", User.class).getResultList();
  }
}

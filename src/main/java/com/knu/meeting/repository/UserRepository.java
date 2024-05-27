package com.knu.meeting.repository;

import com.knu.meeting.model.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    void save(User user);
    void delete(User user);
    List<User> findAll();
}
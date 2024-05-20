package com.knu.meeting.repository;

import com.knu.meeting.model.entity.User;
import java.util.List;

public interface UserRepository {
    User findById(Long id);
    User findByUsername(String username);
    void save(User user);
    void delete(User user);
    List<User> findAll();
}
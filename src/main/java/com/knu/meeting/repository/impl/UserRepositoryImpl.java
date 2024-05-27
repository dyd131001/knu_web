package com.knu.meeting.repository.impl;

import com.knu.meeting.model.entity.User;
import com.knu.meeting.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;

    @Override
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> result = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            // 병합을 통한 변경
            entityManager.merge(user);
        }
    }

    @Override
    public void delete(User user) {
        if (entityManager.contains(user)) {
            entityManager.remove(user);
        } else {
            entityManager.remove(entityManager.merge(user));
        }
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.participations", User.class).getResultList();
    }
}

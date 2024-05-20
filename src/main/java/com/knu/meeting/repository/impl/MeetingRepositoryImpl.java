package com.knu.meeting.repository.impl;

import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.repository.MeetingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
public class MeetingRepositoryImpl implements MeetingRepository {

    private EntityManager entityManager;

    @Override
    public Meeting findById(Long id) {
        return entityManager.find(Meeting.class, id);
    }

    @Override
    public List<Meeting> findMeetingsAfterId(Long id) {
        return entityManager.createQuery("SELECT m FROM Meeting m WHERE m.id > :id", Meeting.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void save(Meeting meeting) {
        if (meeting.getId() == null) {
            entityManager.persist(meeting);
        } else {
            entityManager.merge(meeting);
        }
    }

    @Override
    public void delete(Meeting meeting) {
        if (entityManager.contains(meeting)) {
            entityManager.remove(meeting);
        } else {
            entityManager.remove(entityManager.merge(meeting));
        }
    }

    @Override
    public List<Meeting> findByTitleContaining(String title, int page, int size) {
        return entityManager.createQuery("SELECT m FROM Meeting m WHERE m.title LIKE :title", Meeting.class)
                .setParameter("title", "%" + title + "%")
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<Meeting> findAll() {
        return entityManager.createQuery("SELECT m FROM Meeting m", Meeting.class).getResultList();
    }
}

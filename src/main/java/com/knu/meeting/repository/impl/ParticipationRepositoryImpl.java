package com.knu.meeting.repository.impl;


import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.repository.ParticipationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
public class ParticipationRepositoryImpl implements ParticipationRepository {

    private EntityManager entityManager;

    @Override
    public Participation findById(Long id) {
        return entityManager.find(Participation.class, id);
    }

    @Override
    public List<Participation> findByUser(User user) {
        return entityManager.createQuery("SELECT p FROM Participation p WHERE p.user = :user", Participation.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Participation> findPastParticipationsByUser(User user) {
        return entityManager.createQuery("SELECT p FROM Participation p WHERE p.user = :user AND p.meeting.endTime < CURRENT_TIMESTAMP", Participation.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public void save(Participation participation) {
        if (participation.getId() == null) {
            entityManager.persist(participation);
        } else {
            entityManager.merge(participation);
        }
    }

    @Override
    public void delete(Participation participation) {
        if (entityManager.contains(participation)) {
            entityManager.remove(participation);
        } else {
            entityManager.remove(entityManager.merge(participation));
        }
    }

    @Override
    public List<Participation> findAll() {
        return entityManager.createQuery("SELECT p FROM Participation p", Participation.class).getResultList();
    }
}

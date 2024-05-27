package com.knu.meeting.repository.impl;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.repository.MeetingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
public class MeetingRepositoryImpl implements MeetingRepository {

    private EntityManager entityManager;

    @Override
    public Optional<Meeting> findById(Long id) {
        Meeting meeting = entityManager.find(Meeting.class, id);
        return Optional.ofNullable(meeting);
    }

    @Override
    public List<Meeting> findMeetingsAfterId(Long id, Address address, Hobby hobby) {
        return entityManager.createQuery("SELECT m FROM Meeting m WHERE m.id > :id" +
                        "AND  m.location.address = :address" +
                        "AND :hobby MEMBER OF m.hobbies", Meeting.class)
                .setParameter("id", id)
                .setParameter("address", address)
                .setParameter("hobby", hobby)
                .getResultList();
    }

    @Override
    public List<Meeting> findAllByLocationAndHobby(String locationName, Address address , Hobby hobby) {
        // WHERE m.title LIKE :title
        return entityManager.createQuery(
                        "SELECT m FROM Meeting m " +
                                "WHERE m.location.locationName LIKE :locationName " +
                                "AND m.location.address = :address " +
                                "AND :hobby MEMBER OF m.hobbies", Meeting.class)
                .setParameter("locationName", "%" + locationName + "%")
                .setParameter("address", address)
                .setParameter("hobby", hobby)
                .getResultList();
    }

    @Override
    public List<Meeting> findAllByLocation(Location location, Hobby hobby) {
        return entityManager.createQuery(
                        "SELECT m FROM Meeting m " +
                                "WHERE m.location = :location " +
                                "AND :hobby MEMBER OF m.hobbies", Meeting.class)
                .setParameter("location", location)
                .setParameter("hobby", hobby)
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
    public List<Meeting> findAllByAddressAndHobby(Address address, Hobby hobby) {
        // WHERE m.title LIKE :title
        return entityManager.createQuery(
                "SELECT m FROM Meeting m WHERE m.location.address = :address" +
                        "AND :hobby MEMBER OF m.hobbies", Meeting.class)
                // List<hobby>인 경우 "AND m.hobbies IN :hobbies" 사용
                //.setParameter("title", "%" + title + "%")
                .setParameter("address", address)
                .setParameter("hobby", hobby)
                .getResultList();
    }

    @Override
    public List<Meeting> findAll() {
        return entityManager.createQuery("SELECT m FROM Meeting m", Meeting.class).getResultList();
    }
}

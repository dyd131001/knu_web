package com.knu.meeting.repository.impl;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.repository.LocationRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
public class LocationRepositoryImpl implements LocationRepository {
    private EntityManager entityManager;

    @Override
    public Optional<Location> findById(Long id) {
        Location location = entityManager.find(Location.class, id);
        return Optional.ofNullable(location);
    }

    @Override
    public void save(Location location) {
        if (location.getId() == null) {
            entityManager.persist(location);
        } else {
            // 병합을 통한 변경
            entityManager.merge(location);
        }
    }

    @Override
    public void delete(Location location) {
        if (entityManager.contains(location)) {
            entityManager.remove(location);
        } else {
            entityManager.remove(entityManager.merge(location));
        }
    }

    @Override
    public List<Location> findAllByAddressAndHobby(Address address, Hobby hobby){
        return entityManager.createQuery(
                        "SELECT l FROM Location l WHERE l.address = :address " +
                                "AND EXISTS (" +
                                "   SELECT 1 FROM Meeting m " +
                                "   WHERE m.location = l AND :hobby MEMBER OF m.hobbies" +
                                ")", Location.class)
                .setParameter("address", address)
                .setParameter("hobby", hobby)
                .getResultList();

    }

    @Override
    public List<Location> findAll() {
        return entityManager.createQuery("SELECT l FROM Location l", Location.class).getResultList();
    }
}

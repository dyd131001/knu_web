package com.knu.meeting.repository.impl;

import com.knu.meeting.model.entity.GroupMessage;
import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.repository.MessageRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    private EntityManager entityManager;

    @Override
    public void save(GroupMessage groupMessage){
        if (groupMessage.getId() == null) {
            entityManager.persist(groupMessage);
        } else {
            entityManager.merge(groupMessage);
        }
    }

    @Override
    public void delete(GroupMessage groupMessage){
        if (entityManager.contains(groupMessage)) {
            entityManager.remove(groupMessage);
        } else {
            entityManager.remove(entityManager.merge(groupMessage));
        }
    }
}



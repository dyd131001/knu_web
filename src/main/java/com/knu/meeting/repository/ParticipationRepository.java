package com.knu.meeting.repository;

import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import java.util.List;

public interface ParticipationRepository {
    Participation findById(Long id);
    List<Participation> findByUser(User user);
    List<Participation> findPastParticipationsByUser(User user);
    void save(Participation participation);
    void delete(Participation participation);
    List<Participation> findAll();
}

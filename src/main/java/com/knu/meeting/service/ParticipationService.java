package com.knu.meeting.service;

import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.repository.ParticipationRepository;
import com.knu.meeting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipationService {

    private ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository){
        this.participationRepository = participationRepository;
    }

    public Participation findById(Long id) {
        return participationRepository.findById(id);
    }

    public List<Participation> findByUser(User user) {
        return participationRepository.findByUser(user);
    }

    public List<Participation> findPastParticipationsByUser(User user) {
        return participationRepository.findPastParticipationsByUser(user);
    }
}
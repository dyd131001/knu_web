package com.knu.meeting.service;

import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.repository.MeetingRepository;
import com.knu.meeting.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MeetingService {

    private MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository){
        this.meetingRepository = meetingRepository;
    }

    public Meeting findById(Long id) {
        return meetingRepository.findById(id);
    }

    public List<Meeting> findMeetingsAfterId(Long id) {
        return meetingRepository.findMeetingsAfterId(id);
    }

    public void save(Meeting meeting) {
        meetingRepository.save(meeting);
    }

    public void delete(Meeting meeting) {
        meetingRepository.delete(meeting);
    }

    public List<Meeting> findByTitleContaining(String title, int page, int size) {
        return meetingRepository.findByTitleContaining(title, page, size);
    }

    public List<Meeting> findAll() {
        return meetingRepository.findAll();
    }
}
